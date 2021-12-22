package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization.HelperObjects.AnonymizedDataSetGenerator;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization.HelperObjects.ArxAnonymizer;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization.HelperObjects.ArxDataSetGenerator;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization.HelperObjects.DataSetAnonymizationResult;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.AnonymizedDataSetData;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import org.deidentifier.arx.ARXPopulationModel;
import org.deidentifier.arx.Data.DefaultData;
import org.deidentifier.arx.DataHandle;

import java.io.IOException;


public class DataSetAnonymizationController {
    private static final ARXPopulationModel population = ARXPopulationModel.create(ARXPopulationModel.Region.USA);

    public DataContainer anonymizeDataSet(DataContainer dataSetContainer) {
        //Prepare the dataset for arx
        DefaultData arxData = ArxDataSetGenerator.generateArxDataSet(dataSetContainer);

        //Run the anonymization
        DataHandle dataHandle;
        try {
            dataHandle = ArxAnonymizer.anonymize(arxData, population);
        } catch (IOException e) {
            throw new DataSetAnonymizationException("couldn't run the anonymization process");
        }

        //Convert the arx result into an anonymized dataset object
        DataContainer anonymizedDataSetContainer = AnonymizedDataSetGenerator.generateDataSetData(dataHandle, population);

        return anonymizedDataSetContainer;
    }
}
