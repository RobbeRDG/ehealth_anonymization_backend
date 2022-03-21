package be.kul.scriptExecutor.Service.SubService;

import be.kul.scriptExecutor.Entity.AnonymizedPersonInformation;
import be.kul.scriptExecutor.Repository.AnonymizationRepository;
import be.kul.scriptExecutor.Repository.MedicalDataRepository;
import be.kul.scriptExecutor.Utils.Enums.AnonymizationStatus;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.AnonymizationInformation.AnonymizationInformation;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class DataHandler {
    @Autowired
    private MedicalDataRepository medicalDataRepository;

    @Autowired
    private AnonymizationRepository anonymizationRepository;

    public DataContainer getMedicalDataSet(String query) {
        //Get the medical data from the database
        List<HashMap<String,String>> dataSet = medicalDataRepository.executeQueryResponse(query);

        //Build the dataset object
        DataSetData dataSetData = new DataSetData(dataSet);

        //Build the anonymization information
        AnonymizationInformation anonymizationInformation = new AnonymizationInformation(AnonymizationStatus.UNANONYMIZED, null);

        //Build the data container
        DataContainer dataContainer = new DataContainer();
        dataContainer.setAssignedData(dataSetData);
        dataContainer.setAnonymizationInformation(anonymizationInformation);

        return dataContainer;
    }


    public AnonymizedPersonInformation getAnonymizedPersonInformation(long personId) {
        //Set the person key string
        String personKey = Long.toString(personId);
        return anonymizationRepository.findAnonymizedPersonById(personKey);
    }

    public DataSetData getPopulationTableDataSet(String populationTableIdentifier) {
        return anonymizationRepository.findPopulationTableById(populationTableIdentifier);
    }
}
