package be.kul.scriptExecutor.Service;

import be.kul.scriptExecutor.Controller.AMQP.AmqpProducerController;
import be.kul.scriptExecutor.Utils.ScriptAnonymizationResult.ScriptAnonymizationResult;
import be.kul.scriptExecutor.Repository.MedicalDataRepository;
import be.kul.scriptExecutor.Service.SubService.ScriptExecutionController;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ScriptSummary;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private AmqpProducerController amqpProducerController;


    public ScriptAnonymizationResult handleAnonymizationRequest(ScriptSummary scriptSummary) {
        log.info("Handling new anonymization request with script id: " + scriptSummary.getScriptId());

        //Execute the script summary
        ScriptAnonymizationResult scriptAnonymizationResult = scriptExecutionController.executeSummary(scriptSummary);

        //Send the result to the user access service to save
        try {
            amqpProducerController.sendAnonymizationResult(scriptAnonymizationResult);
        } catch (JsonProcessingException e) {
            log.error("something went wrong processing the anonymization result for amqp: " + e.getMessage());
        }

        return scriptAnonymizationResult;
    }

    public DataContainer getDataSet(String query) {
        return medicalDataRepository.executeQueryResponse(query);
    }
}
