package be.kul.scriptExecutor.Service.SubService;

import be.kul.scriptExecutor.Entity.AnonymizedPersonInformation;
import be.kul.scriptExecutor.Utils.DataSetAnonymization.Anonymizer.ArxAnonymizer;
import be.kul.scriptExecutor.Utils.DataSetAnonymization.DataSetGenerators.DataContainerToArxGenerator;
import be.kul.scriptExecutor.Utils.DataSetAnonymization.DataSetGenerators.ArxToDataContainerGenerator;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import org.deidentifier.arx.ARXPopulationModel;
import org.deidentifier.arx.Data;
import org.deidentifier.arx.Data.DefaultData;
import org.deidentifier.arx.DataHandle;
import org.deidentifier.arx.DataSubset;
import org.deidentifier.arx.aggregates.StatisticsQuality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;


@Component
public class DataSetAnonymizationController {
    private static final ARXPopulationModel population = ARXPopulationModel.create(ARXPopulationModel.Region.FRANCE);
    private DataHandler dataHandler;

    @Autowired
    public DataSetAnonymizationController(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public DataContainer anonymizeDataSet(DataContainer dataSetContainer, String anonymizationLevelIdentifier) {
        //Generate arx data from the selected dataset
        DefaultData researchData = DataContainerToArxGenerator.generateArxDataSet((DataSetData) dataSetContainer.getAssignedData());

        //Test if the dataset can be anonymized
        validateAnonymizationConstraints(researchData);

        //Get the column names of the research subset
        List<String> QINames = new ArrayList<>();
        QINames.addAll(researchData.getDefinition().getQuasiIdentifyingAttributes());
        QINames.add("person_id");

        //Get the population table dataset
        String populationTableIdentifier = "population";
        DataSetData populationTableDataSet = dataHandler.getPopulationTableDataSet(populationTableIdentifier);

        //Generate arx data from the population table
        DefaultData populationTable = DataContainerToArxGenerator.generateArxDataSet(populationTableDataSet, QINames);

        //Create a subset using the population and the research data
        DataSubset researchSubset = DataSubset.create(populationTable,researchData);

        //Create a new Arx anonymizer
        ArxAnonymizer arxAnonymizer = new ArxAnonymizer();

        //Run the anonymization in arx
        DataHandle dataHandle;
        try {
            dataHandle = arxAnonymizer.anonymize(researchSubset, researchData);
        } catch (IOException e) {
            throw new DataSetAnonymizationException("couldn't run the anonymization : IO exception");
        }


        //Convert the arx result into an anonymized dataset object
        DataContainer anonymizedDataSetContainer = ArxToDataContainerGenerator.generateDataSetData(dataHandle, population);

        return anonymizedDataSetContainer;
    }

    private void validateAnonymizationConstraints(DefaultData researchData) {
        DataHandle dataHandle = researchData.getHandle();

        //Test if the dataset only contains one entry per individual
        //TODO
    }

    private DataContainer filterQuasiAttributes(DataContainer dataSetContainer, String anonymizationLevelIdentifier) {
        //get the dataset from the container
        DataSetData dataSetData = (DataSetData) dataSetContainer.getAssignedData();

        //Check the first record to see if the data contains personal information, else return the unmodified data container
        if (!dataSetData.getUnanonymizedDataSet().get(0).containsKey("person_id")) return dataSetContainer;

        //Iterate over each person in the dataset
        for (HashMap<String,String> personRecord : dataSetData.getUnanonymizedDataSet()) {
            //Get the person id
            long personId = Long.parseLong(personRecord.get("person_id"));

            //Retrieve the anonymized person information
            AnonymizedPersonInformation anonymizedPersonInformation = dataHandler.getAnonymizedPersonInformation(personId);

            //For each attribute of the anonymised person information, replace the value
            for (Map.Entry<String, HashMap<String,String>> attribute : anonymizedPersonInformation.getAnonymizedAttributes().entrySet()) {
                //Get the attribute name
                String attributeName = attribute.getKey();

                //If the dataset record contains the personal attribute, replace the value with the anonymized one
                if (personRecord.containsKey(attributeName)) {
                    //Get the anonymized attribute for the requested level
                    String anonymizedAttribute = attribute.getValue().get(anonymizationLevelIdentifier);

                    //replace attribute value
                    personRecord.put(attributeName, anonymizedAttribute);
                }
            }
        }

        return dataSetContainer;
    }

    public String testDPresenceAnonymization(DataContainer researchDataContainer, double deltaStart, double deltaStop, double deltaStep) {
        //Generate arx data from the selected dataset
        DefaultData researchData = DataContainerToArxGenerator.generateArxDataSet((DataSetData) researchDataContainer.getAssignedData());

        //Test if the dataset can be anonymized
        validateAnonymizationConstraints(researchData);

        //Get the column names of the research subset
        List<String> QINames = new ArrayList<>();
        QINames.addAll(researchData.getDefinition().getQuasiIdentifyingAttributes());
        QINames.add("person_id");

        //Get the population table dataset
        String populationTableIdentifier = "population";
        DataSetData populationTableDataSet = dataHandler.getPopulationTableDataSet(populationTableIdentifier);

        //Generate arx data from the population table
        DefaultData populationTable = DataContainerToArxGenerator.generateArxDataSet(populationTableDataSet, QINames);

        //Create a subset using the population and the research data
        DataSubset researchSubset = DataSubset.create(populationTable,researchData);

        //Create the csv string
        StringBuilder sb = new StringBuilder();
        sb.append("SE;granularity;nonUniformEntropy");

        //Run the anonymization in arx for each of the specified delta values
        for (double delta = deltaStart; delta <= deltaStop; delta += deltaStep) {
            //Create a new Arx anonymizer
            ArxAnonymizer arxAnonymizer = new ArxAnonymizer();

            //Run the anonymization
            DataHandle dataHandle;
            try {
                dataHandle = arxAnonymizer.anonymizeWithTestDelta(researchSubset, researchData, delta);
            } catch (IOException e) {
                throw new DataSetAnonymizationException("couldn't run the anonymization : IO exception");
            }

            //Set the delta string
            String deltaString = String.format(Locale.FRANCE, "%-10.4f", delta);

            //Get the quality statistics
            StatisticsQuality statisticsQuality = dataHandle.getStatistics().getQualityStatistics();

            //Squared error
            String SE = String.format(Locale.FRANCE, "%-10.4f", statisticsQuality.getAttributeLevelSquaredError().getArithmeticMean());
            String granularity = String.format(Locale.FRANCE, "%-10.4f", statisticsQuality.getGranularity().getArithmeticMean());
            String nonUniformEntropy = String.format(Locale.FRANCE, "%-10.4f", statisticsQuality.getNonUniformEntropy().getArithmeticMean());

            dataHandle.release();

            sb.append(deltaString).append(";").append(SE).append(granularity).append(nonUniformEntropy).append(System.getProperty("line.separator"));;
        }

        return sb.toString();
    }
}
