package be.kul.scriptExecutor.Service;

import be.kul.scriptExecutor.Controller.AMQP.AmqpProducerController;
import be.kul.scriptExecutor.Entity.AnonymizedPersonInformation;
import be.kul.scriptExecutor.Service.SubService.DataHandler;
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
    private DataHandler dataHandler;

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

        log.info("Handled anonymization request with script id: " + scriptSummary.getScriptId());

        return scriptAnonymizationResult;
    }

    public DataContainer getMedicalDataSet(String query) {
        return dataHandler.getMedicalDataSet(query);
    }

    public AnonymizedPersonInformation getAnonymizedPersonInformation(long personId) {
        return dataHandler.getAnonymizedPersonInformation(personId);
    }

    public String testDPresenceAnonymization(String sqlQuery, double deltaStart, double deltaStop, double deltaStep) {
        return scriptExecutionController.testDPresenceAnonymization(sqlQuery,deltaStart,deltaStop,deltaStep);
    }
}
