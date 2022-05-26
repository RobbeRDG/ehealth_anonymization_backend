package be.kul.scriptExecutor.Utils.DataSetAnonymization.DataSetGenerators;

import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.AnonymizationInformation.AnonymizationParameters.DataSetAnonymizationParameters;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.AnonymizationInformation.AnonymizationInformation;
import be.kul.scriptExecutor.Utils.Enums.AnonymizationStatus;
import org.deidentifier.arx.ARXPopulationModel;
import org.deidentifier.arx.DataHandle;
import org.deidentifier.arx.risk.RiskModelSampleSummary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArxToDataContainerGenerator {
    public static DataContainer generateDataSetData(DataHandle dataHandle, ARXPopulationModel population) {
        //Convert arx dataset to hashmap
        List<HashMap<String,String>> dataSetMap = generateDataSetMap(dataHandle);

        //Create a dataset object
        DataSetData anonymizedDataSetData = new DataSetData(dataSetMap);

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
        DataSetAnonymizationParameters dataSetAnonymizationParameters = new DataSetAnonymizationParameters();

        //Get the sample risks
        RiskModelSampleSummary riskModelSampleSummary = dataHandle.getView().getRiskEstimator().getSampleBasedRiskSummary(0.1d);

        //From the sample risks extract the risks
        double averageJournalistRisk = riskModelSampleSummary.getJournalistRisk().getAverageRisk();
        double highestJournalistRisk = riskModelSampleSummary.getJournalistRisk().getHighestRisk();

        //Set the risks
        dataSetAnonymizationParameters.setAverageJournalistRisk(averageJournalistRisk);
        dataSetAnonymizationParameters.setHighestJournalistRisk(highestJournalistRisk);

        //Get the number of suppressed records
        int numberOfSuppressedRecords = calculateNumberOfSuppressedRecords(dataHandle);

        //Set the suppression count
        dataSetAnonymizationParameters.setSuppressionCount(numberOfSuppressedRecords);

        return dataSetAnonymizationParameters;
    }

    private static int calculateNumberOfSuppressedRecords(DataHandle dataHandle) {
        int suppressedCount = 0;

        for (int rowIndex = 0; rowIndex < dataHandle.getNumRows(); rowIndex++) {
            if (dataHandle.isSuppressed(rowIndex)) suppressedCount++;
        }

        return suppressedCount;
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
