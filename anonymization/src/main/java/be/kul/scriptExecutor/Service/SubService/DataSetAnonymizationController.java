package be.kul.scriptExecutor.Service.SubService;

import be.kul.scriptExecutor.Utils.DataSetAnonymization.DataSetGenerators.DataContainerToArxGenerator;
import be.kul.scriptExecutor.Utils.DataSetAnonymization.DataSetGenerators.ArxToDataContainerGenerator;
import be.kul.scriptExecutor.Utils.DataSetAnonymization.HelperObjects.ArxAnonymizationConfigurer;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import org.deidentifier.arx.*;
import org.deidentifier.arx.Data.DefaultData;
import org.deidentifier.arx.aggregates.StatisticsQuality;
import org.deidentifier.arx.risk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;


@Component
public class DataSetAnonymizationController {
    private static final ARXPopulationModel population = ARXPopulationModel.create(ARXPopulationModel.Region.USA);
    private DataHandler dataHandler;

    @Autowired
    public DataSetAnonymizationController(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public DataContainer anonymizeDataSet(DataContainer researchDataContainer) {
        // Manually set delta
        double delta = 0.2;

        //Generate arx data from the selected dataset
        DefaultData researchData = DataContainerToArxGenerator.generateArxDataSet((DataSetData) researchDataContainer.getAssignedData(), false);

        //Test if the dataset can be anonymized
        //TODO minimum and maximum fraction of population

        //Get the column names of the research subset
        List<String> QINames = new ArrayList<>();
        QINames.addAll(getIdentifierAttributeNames(researchData));

        //Get the population table dataset
        String populationTableIdentifier = "population";
        DataSetData populationTableDataSet = dataHandler.getPopulationTableDataSet(populationTableIdentifier);

        //Generate arx data from the population table
        DefaultData populationTable = DataContainerToArxGenerator.generateArxDataSet(populationTableDataSet, QINames, true);

        //Create a subset using the population and the research data
        DataSubset researchSubset = DataSubset.create(populationTable,researchData);

        //Run the anonymization in arx
        DataHandle dataHandle;
        try {
            dataHandle = anonymizeWithManualDelta(researchSubset, researchData, populationTable, delta);
        } catch (IOException e) {
            throw new DataSetAnonymizationException("couldn't run the anonymization : IO exception");
        }

        //Convert the arx result into an anonymized dataset object
        DataContainer anonymizedDataSetContainer = ArxToDataContainerGenerator.generateDataSetData(dataHandle.getView(), population);

        return anonymizedDataSetContainer;
    }

    public String testDPresenceAnonymizationStats(DataContainer researchDataContainer, double deltaStart, double deltaStop, double deltaStep) {
        //Generate arx data from the selected dataset
        DefaultData researchData = DataContainerToArxGenerator.generateArxDataSet((DataSetData) researchDataContainer.getAssignedData(), false);

        //Test if the dataset can be anonymized
        //TODO

        //Get the column names of the research subset
        List<String> QINames = new ArrayList<>();
        QINames.addAll(getIdentifierAttributeNames(researchData));

        //Get the population table dataset
        String populationTableIdentifier = "population";
        DataSetData populationTableDataSet = dataHandler.getPopulationTableDataSet(populationTableIdentifier);

        //Generate arx data from the population table
        DefaultData populationTable = DataContainerToArxGenerator.generateArxDataSet(populationTableDataSet, QINames, true);

        //Create a subset using the population and the research data
        DataSubset researchSubset = DataSubset.create(populationTable,researchData);

        //Create the csv string
        StringBuilder sb = new StringBuilder();
        sb.append("Delta;Non-Uniform Entropy;Granularity;Discernibility;Highest Journalist Risk;Missing");
        sb.append(System.getProperty("line.separator"));


        //Run the anonymization in arx for each of the specified delta values
        for (double delta = deltaStart; delta <= deltaStop; delta += deltaStep) {
            //Run the anonymization
            DataHandle dataHandle;
            try {
                dataHandle = anonymizeWithManualDelta(researchSubset, researchData, populationTable, delta);
            } catch (IOException e) {
                throw new DataSetAnonymizationException("couldn't run the anonymization : IO exception");
            } catch (DataSetAnonymizationException e) {
                continue;
            }

            //Set the delta string
            String deltaString = String.format(Locale.FRANCE, "%.4f", delta);

            //Get the quality statistics
            StatisticsQuality statisticsQuality = dataHandle.getView().getStatistics().getQualityStatistics();

            //Squared error
            String nonUniformEntropy = String.format(Locale.FRANCE, "%.8f", statisticsQuality.getNonUniformEntropy().getArithmeticMean());
            String granularity = String.format(Locale.FRANCE, "%.8f", statisticsQuality.getGranularity().getArithmeticMean());
            String discernibility = String.format(Locale.FRANCE, "%.8f", statisticsQuality.getDiscernibility().getValue());
            String missings = String.format(Locale.FRANCE, "%.8f", statisticsQuality.getMissings().getArithmeticMean());


            //Get the risk models
            RiskModelSampleSummary riskModelSampleSummary = dataHandle.getView().getRiskEstimator().getSampleBasedRiskSummary(0.1d);

            //Journalist risk
            String journalistRisk = String.format(Locale.FRANCE, "%.8f", riskModelSampleSummary.getJournalistRisk().getHighestRisk());

            sb
                    .append(deltaString)
                    .append(";")
                    .append(nonUniformEntropy)
                    .append(";")
                    .append(granularity)
                    .append(";")
                    .append(discernibility)
                    .append(";")
                    .append(journalistRisk)
                    .append(";")
                    .append(missings)
                    .append(";")
                    .append(System.getProperty("line.separator"));
        }

        return sb.toString();
    }

    public DataContainer testDPresenceAnonymization(DataContainer researchDataContainer, double delta) {
        //Generate arx data from the selected dataset
        DefaultData researchData = DataContainerToArxGenerator.generateArxDataSet((DataSetData) researchDataContainer.getAssignedData(), false);

        //Test if the dataset can be anonymized
        //TODO

        //Get the column names of the research subset
        List<String> QINames = new ArrayList<>();
        QINames.addAll(getIdentifierAttributeNames(researchData));

        //Get the population table dataset
        String populationTableIdentifier = "population";
        DataSetData populationTableDataSet = dataHandler.getPopulationTableDataSet(populationTableIdentifier);

        //Generate arx data from the population table
        DefaultData populationTable = DataContainerToArxGenerator.generateArxDataSet(populationTableDataSet, QINames, true);

        //Create a subset using the population and the research data
        DataSubset researchSubset = DataSubset.create(populationTable,researchData);

        //Run the anonymization in arx
        DataHandle dataHandle;
        try {
            dataHandle = anonymizeWithManualDelta(researchSubset, researchData, populationTable, delta);
        } catch (IOException e) {
            throw new DataSetAnonymizationException("couldn't run the anonymization : IO exception");
        }


        //Convert the arx result into an anonymized dataset object
        DataContainer anonymizedDataSetContainer = ArxToDataContainerGenerator.generateDataSetData(dataHandle.getView(), population);

        return anonymizedDataSetContainer;
    }

    private List<String> getIdentifierAttributeNames(DefaultData researchData) {
        DataHandle dataHandle = researchData.getHandle();

        List<String> identifierNames = new ArrayList<>();
        for (int i=0; i<dataHandle.getNumColumns(); i++) {
            String attributeName = dataHandle.getAttributeName(i);

            switch (attributeName) {
                case "person_id":
                case "gender_concept_id":
                case "birth_datetime":
                case "race_concept_id":
                    identifierNames.add(attributeName);
                    break;
                default:
                    break;
            }
        }

        return identifierNames;
    }

    public DataHandle anonymizeWithManualDelta(DataSubset researchSubset, DefaultData researchData, DefaultData populationTable, double delta) throws IOException {
        //Create a configuration object
        ARXConfiguration configuration = ARXConfiguration.create();

        //Pass the configuration to the anonymization configurer
        ArxAnonymizationConfigurer.configureWithTestDelta(researchSubset, configuration, 0, delta, 11);

        //Set the anonymizer
        ARXAnonymizer anonymizer = new ARXAnonymizer();

        //Release the research data handle if needed
        if (!researchData.getHandle().isReleased()) researchData.getHandle().release();

        //Release the population table data handle if needed
        if (!populationTable.getHandle().isReleased()) populationTable.getHandle().release();

        //Execute the anonymization
        ARXResult result = anonymizer.anonymize(populationTable,configuration);

        //Get a handle on the data
        DataHandle dataHandle = result.getOutput();

        //throw error if arx couldn't find an appropriate anonymization
        if (dataHandle == null) throw new DataSetAnonymizationException(
                "No privacy preserving transformation could be executed"
        );

        return dataHandle;
    }
}
