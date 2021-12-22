package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization.HelperObjects;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.AnonymizationInformation.AnonymizationInformation;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.AnonymizationInformation.AnonymizationParameters.DataSetAnonymizationParameters;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.AnonymizedDataSetData;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.enums.AnonymizationStatus;
import org.deidentifier.arx.ARXPopulationModel;
import org.deidentifier.arx.ARXResult;
import org.deidentifier.arx.DataHandle;
import org.deidentifier.arx.risk.RiskModelSampleRisks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnonymizedDataSetGenerator {
    public static DataContainer generateDataSetData(DataHandle dataHandle, ARXPopulationModel population) {
        //Convert arx dataset to hashmap
        List<HashMap<String,String>> dataSetMap = generateDataSetMap(dataHandle);

        //Create an anonymized dataset object
        AnonymizedDataSetData anonymizedDataSetData = new AnonymizedDataSetData(dataSetMap);

        //Get the anonymization parameters
        DataSetAnonymizationParameters parameters = generateDataSetAnonymizationParameters(dataHandle, population);

        //set the anonymization information
        AnonymizationInformation anonymizationInformation = new AnonymizationInformation(AnonymizationStatus.ANONYMIZED_DATASET, parameters);

        //Create the data container
        DataContainer dataContainer = new DataContainer();
        dataContainer.setAssignedData(anonymizedDataSetData);
        dataContainer.setAnonymizationInformation(anonymizationInformation);

        return dataContainer;
    }

    private static DataSetAnonymizationParameters generateDataSetAnonymizationParameters(DataHandle dataHandle, ARXPopulationModel population) {
        //Get the sample risks
        RiskModelSampleRisks sampleRisks = dataHandle.getRiskEstimator(population).getSampleBasedReidentificationRisk();

        //From the sample risks extract the risks
        double averageReIdentificationRisk = sampleRisks.getAverageRisk();
        double lowestRisk = sampleRisks.getLowestRisk();
        double numberOfAffectedByLowestRisk = sampleRisks.getNumRecordsAffectedByLowestRisk();
        double highestRisk = sampleRisks.getHighestRisk();
        double numberOfAffectedByHighestRisk = sampleRisks.getNumRecordsAffectedByHighestRisk();

        //Set the risks
        DataSetAnonymizationParameters dataSetAnonymizationParameters = new DataSetAnonymizationParameters();
        dataSetAnonymizationParameters.setReIdentificationRisk(averageReIdentificationRisk);
        dataSetAnonymizationParameters.setHighestRisk(highestRisk);
        dataSetAnonymizationParameters.setNumberOfAffectedByHighestRisk(numberOfAffectedByHighestRisk);
        dataSetAnonymizationParameters.setLowestRisk(lowestRisk);
        dataSetAnonymizationParameters.setNumberOfAffectedByLowestRisk(numberOfAffectedByLowestRisk);

        return dataSetAnonymizationParameters;
    }

    private static List<HashMap<String, String>> generateDataSetMap(DataHandle dataHandle) {
        //Get the number of columns and rows
        int numberOfColumns = dataHandle.getNumColumns();
        int numberOfRows = dataHandle.getNumRows();

        //Get the column names
        List<String> columnNames = new ArrayList<>();
        for (int i=0; i<numberOfColumns; i++) {
            columnNames.add(dataHandle.getAttributeName(i));
        }

        //Fill the data map
        List<HashMap<String,String>> dataSetMap = new ArrayList<>();
        for (int row=0; row<numberOfRows; row++) {
            HashMap<String,String> record = new HashMap<>();
            for (int column=0; column<numberOfColumns; column++) {
                //Get the column name
                String columnName = columnNames.get(column);

                //Get the attribute value
                String value = dataHandle.getValue(row, column);

                record.put(columnName,value);
            }
            dataSetMap.add(record);
        }

        return dataSetMap;
    }

}
