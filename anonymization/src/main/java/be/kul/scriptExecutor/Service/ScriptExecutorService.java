package be.kul.scriptExecutor.Service;

import be.kul.scriptExecutor.Repository.MedicalDataRepository;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.ScriptExecutionController;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeStructure.ScriptSummary.ScriptSummary;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataContainer.DataContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class ScriptExecutorService {
    @Autowired
    @Lazy
    private ScriptExecutionController scriptExecutionController;

    @Autowired
    private MedicalDataRepository medicalDataRepository;


    public ResponseEntity<HashMap<String, DataContainer>> executeSummary(ScriptSummary scriptSummary) {
        return scriptExecutionController.executeSummary(scriptSummary);
    }

    public DataContainer getDataSet(String query) {
        return medicalDataRepository.executeQueryResponse(query);
    }
}
