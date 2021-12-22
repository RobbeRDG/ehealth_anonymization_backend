package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization.HelperObjects;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import org.deidentifier.arx.AttributeType;
import org.deidentifier.arx.Data;
import org.deidentifier.arx.DataHandle;
import org.deidentifier.arx.DataType;
import org.deidentifier.arx.aggregates.HierarchyBuilder;
import org.deidentifier.arx.aggregates.HierarchyBuilderDate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class ArxDataSetGenerator {
    private static final HashMap<String, ArxColumnInformation> columnInformation = initialiseColumnInformation();

    public static Data.DefaultData generateArxDataSet(DataContainer dataSetContainer) {
        //Get the dataset data
        DataSetData dataSetData = (DataSetData) dataSetContainer.getAssignedData();

        //Get the unanonymized dataset
        List<HashMap<String, String>> unanonymizedDataSet = dataSetData.getUnanonymizedDataSet();

        //Get the column names
        List<String> columnNames = getColumnNames(unanonymizedDataSet);

        //Validate if the columns of the dataset are supported
        validateColumnNames(columnNames);

        //Build the arx data object
        Data.DefaultData arxData = addDataToArxDataSet(columnNames, unanonymizedDataSet);

        //Add the column information to the data
        addColumnInformation(columnNames, arxData);

        return arxData;
    }

    private static void addColumnInformation(List<String> columnNames, Data.DefaultData arxData) {
        DataHandle handle = arxData.getHandle();

        for (String columnName : columnNames) {
            //Add the dataType
            arxData.getDefinition().setDataType(
                    columnName,
                    columnInformation.get(columnName).getDataType()
            );

            //Add the attribute type
            arxData.getDefinition().setAttributeType(
                    columnName,
                    columnInformation.get(columnName).getAttributeType()
            );

            //Add the hierarchy
            int columnIndex = handle.getColumnIndexOf(columnName);
            arxData.getDefinition().setHierarchy(
                    columnName,
                    columnInformation.get(columnName).getHierarchy(handle.getDistinctValues(columnIndex))
            );
        }
    }

    private static void validateColumnNames(List<String> columnNames) {
        for (String columnName  : columnNames) {
            if (!columnInformation.containsKey(columnName)) {
                throw new DataSetAnonymizationException("datasets with column name: " + columnName + " are not supported");
            }
        }
    }

    private static Data.DefaultData addDataToArxDataSet(List<String> columnNames, List<HashMap<String, String>> unanonymizedDataSet) {
        //Create the data object
        Data.DefaultData arxData = Data.create();

        //Add the column names to the data object
        arxData.add(columnNames.toArray(new String[0]));

        //Iterate over the list of data entries
        for (HashMap<String, String> record : unanonymizedDataSet) {
            //Add the string attributes to a list
            List<String> recordStringList = new ArrayList<>();
            for (String columnName : columnNames) {
                recordStringList.add(record.get(columnName));
            }

            //Add that list of string to the arx data
            String[] recordStringArray = recordStringList.toArray(new String[0]);
            arxData.add(recordStringArray);
        }

        return arxData;
    }

    private static List<String> getColumnNames(List<HashMap<String, String>> unanonymizedDataSet) {
        return new ArrayList<>(unanonymizedDataSet.get(0).keySet());
    }

    private static HashMap<String, ArxColumnInformation> initialiseColumnInformation() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //Initialise the column information for arx
        HashMap<String, ArxColumnInformation> columnInformation = new HashMap<>();
        try {
            //Person id column
            ArxColumnInformation personIdColumn = new ArxColumnInformation("person_id");
            personIdColumn.setDataType(DataType.INTEGER);
            personIdColumn.setAttributeType(AttributeType.IDENTIFYING_ATTRIBUTE);
            personIdColumn.setHierarchy(AttributeType.Hierarchy.create());
            columnInformation.put(personIdColumn.getColumnName(), personIdColumn);

            //Gender concept column
            ArxColumnInformation genderConceptColumn = new ArxColumnInformation("gender_concept_id");
            genderConceptColumn.setDataType(DataType.INTEGER);
            genderConceptColumn.setAttributeType(AttributeType.QUASI_IDENTIFYING_ATTRIBUTE);
            genderConceptColumn.setHierarchy(
                    AttributeType.Hierarchy.create(classLoader.getResourceAsStream("ARX/hierarchies/gender_concept_id_hierarchy.csv"), Charset.defaultCharset(), ',')
            );
            columnInformation.put(genderConceptColumn.getColumnName(), genderConceptColumn);

            //Birth datetime column
            ArxColumnInformation birthDateTimeColumn = new ArxColumnInformation("birth_datetime");
            birthDateTimeColumn.setDataType(DataType.createDate("yyyy-MM-dd hh:mm:ss"));
            birthDateTimeColumn.setAttributeType(AttributeType.QUASI_IDENTIFYING_ATTRIBUTE);
            birthDateTimeColumn.setHierarchy(
                    null
            );
            columnInformation.put(birthDateTimeColumn.getColumnName(), birthDateTimeColumn);

            //Race concept column
            ArxColumnInformation raceConceptColumn = new ArxColumnInformation("race_concept_id");
            raceConceptColumn.setDataType(DataType.INTEGER);
            raceConceptColumn.setAttributeType(AttributeType.QUASI_IDENTIFYING_ATTRIBUTE);
            raceConceptColumn.setHierarchy(
                    AttributeType.Hierarchy.create(classLoader.getResourceAsStream("ARX/hierarchies/race_concept_id_hierarchy.csv"), Charset.defaultCharset(), ',')
            );
            columnInformation.put(raceConceptColumn.getColumnName(), raceConceptColumn);

            //Death datetime column
            ArxColumnInformation deathDateTimeColumn = new ArxColumnInformation("death_datetime");
            deathDateTimeColumn.setDataType(DataType.createDate("yyyy-MM-dd hh:mm:ss"));
            deathDateTimeColumn.setAttributeType(AttributeType.QUASI_IDENTIFYING_ATTRIBUTE);
            deathDateTimeColumn.setHierarchy(
                    null
            );
            columnInformation.put(deathDateTimeColumn.getColumnName(), deathDateTimeColumn);

            //death cause concept column
            ArxColumnInformation deathCauseConceptColumn = new ArxColumnInformation("cause_concept_id");
            deathCauseConceptColumn.setDataType(DataType.INTEGER);
            deathCauseConceptColumn.setAttributeType(AttributeType.SENSITIVE_ATTRIBUTE);
            columnInformation.put(deathCauseConceptColumn.getColumnName(), deathCauseConceptColumn);
        } catch (IOException e) {
            throw new RuntimeException("Problem with reading in column hierarchies");
        }

        return columnInformation;
    }
}
