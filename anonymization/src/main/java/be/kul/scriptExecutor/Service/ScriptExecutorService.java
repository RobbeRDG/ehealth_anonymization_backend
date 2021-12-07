package be.kul.scriptExecutor.Service;

import be.kul.scriptExecutor.Repository.MedicalDataRepository;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.ScriptExecutionController;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.ScriptSummary.ScriptSummary;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Variable.VariableContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class ScriptExecutorService {
    @Autowired
    @Lazy
    private ScriptExecutionController scriptExecutionController;

    @Autowired
    private MedicalDataRepository medicalDataRepository;


    public ResponseEntity<HashMap<String, VariableContainer>> executeSummary(ScriptSummary scriptSummary) {
        return scriptExecutionController.executeSummary(scriptSummary);
    }

    public List<HashMap<String,String>> getDataSet(String query) {
        return medicalDataRepository.executeQueryResponse(query);
    }
}
