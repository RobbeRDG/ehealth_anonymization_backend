package be.kul.scriptExecutor.Service;

import be.kul.scriptExecutor.Utils.ScriptExecutionResult.ScriptExecutionResult;
import be.kul.scriptExecutor.Repository.MedicalDataRepository;
import be.kul.scriptExecutor.Service.SubService.ScriptExecutionController;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ScriptSummary;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScriptExecutorService {
    @Autowired
    @Lazy
    private ScriptExecutionController scriptExecutionController;

    @Autowired
    private MedicalDataRepository medicalDataRepository;


    public ResponseEntity<ScriptExecutionResult> handleAnonymizationRequest(ScriptSummary scriptSummary) {
        return scriptExecutionController.executeSummary(scriptSummary);
    }

    public DataContainer getDataSet(String query) {
        return medicalDataRepository.executeQueryResponse(query);
    }
}
