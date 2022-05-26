package be.kul.scriptExecutor.Utils.DataSetAnonymization.DataSetGenerators;
import be.kul.scriptExecutor.Utils.DataSetAnonymization.HelperObjects.ArxColumnInformationConfigurer;
import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.DataSetData;
import org.deidentifier.arx.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataContainerToArxGenerator {
    public static Data.DefaultData generateArxDataSet(DataSetData dataSetData, boolean setAttributeInformation) {
        //Get the unanonymized dataset
        List<HashMap<String, String>> unanonymizedDataSet = dataSetData.getDataset();

        //Get the column names
        List<String> columnNames = getColumnNames(unanonymizedDataSet);

        //Build the arx data object
        Data.DefaultData arxData = addDataToArxDataSet(columnNames, unanonymizedDataSet);

        //Initialize the attribute information if requested
        if (setAttributeInformation) {
            try {
                //Use only the filter column names to build the dataset
                ArxColumnInformationConfigurer.generateMap(columnNames, arxData);
            } catch (IOException e) {
                throw new DataSetAnonymizationException("Fault with initialisation of attribute information");
            }
        }

        return arxData;
    }

    public static Data.DefaultData generateArxDataSet(DataSetData dataSetData, List<String> filterColumnNames, boolean setAttributeInformation) {
        //Get the unanonymized dataset
        List<HashMap<String, String>> unanonymizedDataSet = dataSetData.getDataset();

        //Get the column names
        List<String> columnNames = getColumnNames(unanonymizedDataSet);

        //Test if all filter column names are contained in the dataset column names
        for (String filterColumnName : filterColumnNames) {
            if (!columnNames.contains(filterColumnName)) throw new DataSetAnonymizationException("Filter column name not in the population table");
        }

        //Build the arx data object
        Data.DefaultData arxData = addDataToArxDataSet(filterColumnNames, unanonymizedDataSet);

        //Initialize the attribute information if requested
        if (setAttributeInformation) {
            try {
                //Use only the filter column names to build the dataset
                ArxColumnInformationConfigurer.generateMap(filterColumnNames, arxData);
            } catch (IOException e) {
                throw new DataSetAnonymizationException("Fault with initialisation of attribute information");
            }
        }

        return arxData;
    }

    /*
    private static void addColumnInformation(List<String> columnNames, HashMap<String, ArxColumnInformation> attributeInformationMap, Data.DefaultData arxData) {
        DataHandle handle = arxData.getHandle();

        for (String columnName : columnNames) {
            //Add the dataType
            arxData.getDefinition().setDataType(
                    columnName,
                    attributeInformationMap.get(columnName).getDataType()
            );

            //Add the attribute type
            arxData.getDefinition().setAttributeType(
                    columnName,
                    attributeInformationMap.get(columnName).getAttributeType()
            );

            //TODO not needed now, maybe in future
            //Add the hierarchy
            int columnIndex = handle.getColumnIndexOf(columnName);
            arxData.getDefinition().setHierarchy(
                    columnName,
                    attributeInformationMap.get(columnName).getHierarchy(handle.getDistinctValues(columnIndex))
            );
        }
    }

     */

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

    public static List<String> getColumnNames(List<HashMap<String, String>> unanonymizedDataSet) {
        return new ArrayList<>(unanonymizedDataSet.get(0).keySet());
    }

}
