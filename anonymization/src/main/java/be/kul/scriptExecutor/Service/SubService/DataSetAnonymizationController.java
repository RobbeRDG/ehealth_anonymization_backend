package be.kul.scriptExecutor.Service.SubService;

import be.kul.scriptExecutor.Entity.AnonymizedPersonInformation;
import be.kul.scriptExecutor.Utils.AnonymizedPersonInformationObjects.AnonymizedValue;
import be.kul.scriptExecutor.Utils.DataSetAnonymization.Anonymizer.ArxAnonymizer;
import be.kul.scriptExecutor.Utils.DataSetAnonymization.DataSetGenerators.DataContainerToArxGenerator;
import be.kul.scriptExecutor.Utils.DataSetAnonymization.DataSetGenerators.ArxToDataContainerGenerator;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import org.deidentifier.arx.ARXPopulationModel;
import org.deidentifier.arx.Data.DefaultData;
import org.deidentifier.arx.DataHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class DataSetAnonymizationController {
    private static final ARXPopulationModel population = ARXPopulationModel.create(ARXPopulationModel.Region.USA);
    private DataHandler dataHandler;

    @Autowired
    public DataSetAnonymizationController(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public DataContainer anonymizeDataSet(DataContainer dataSetContainer, String anonymizationLevelIdentifier) {
        //Replace person quasi identifiers with pre anonymised person information
        dataSetContainer = filterQuasiAttributes(dataSetContainer,anonymizationLevelIdentifier);

        //Get the column names

        //Prepare the dataset for arx
        DefaultData arxData = DataContainerToArxGenerator.generateArxDataSet(dataSetContainer);

        //Run the anonymization in arx
        DataHandle dataHandle;
        try {
            dataHandle = ArxAnonymizer.anonymize(arxData, population);
        } catch (IOException e) {
            throw new DataSetAnonymizationException("couldn't run the anonymization : IO exception");
        }


        //Convert the arx result into an anonymized dataset object
        DataContainer anonymizedDataSetContainer = ArxToDataContainerGenerator.generateDataSetData(dataHandle, population);

        return anonymizedDataSetContainer;
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
}
