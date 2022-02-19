package be.kul.useraccess.Service;

import be.kul.useraccess.Entity.ScriptAnonymizationResult;
import be.kul.useraccess.Service.SubService.DataHandlerController;
import be.kul.useraccess.Entity.ScriptSummary;
import be.kul.useraccess.Service.SubService.ScriptParserController;
import be.kul.useraccess.Utils.Exceptions.ExceptionClasses.ScriptFileToStringConversionException;
import be.kul.useraccess.controller.AMQP.AmqpProducerController;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class UserAccessService {
    @Autowired
    private ScriptParserController scriptParserController;

    @Autowired
    private AmqpProducerController amqpProducerController;

    @Autowired
    private DataHandlerController dataHandlerController;

    public ScriptSummary handleScriptUpload(MultipartFile scriptFile) throws ScriptFileToStringConversionException, JsonProcessingException {
        //Read the file input string
        String scriptString = "";
        try {
            scriptString = new String(scriptFile.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.warn("Something went wrong converting script file to string: " + e.getLocalizedMessage());
            throw new ScriptFileToStringConversionException("Couldn't convert specified script file to string: " + e.getLocalizedMessage());
        }

        //Send the script string to the parser
        ScriptSummary scriptSummary = scriptParserController.generateScriptSummary(scriptString);

        //Save the generated script summary to the database
        scriptSummary = dataHandlerController.saveInputScriptSummary(scriptSummary);

        //Send the summary to the anonymization service
        amqpProducerController.sendAnonymizationRequest(scriptSummary);

        //Logger
        log.info("Handled new input script with id: " + scriptSummary.getScriptId());

        //Return the script summary
        return scriptSummary;
    }


    public void handleScriptExecutionResult(ScriptAnonymizationResult scriptAnonymizationResult) {
        //Save the script execution result to the database
        dataHandlerController.saveScriptExecutionResult(scriptAnonymizationResult);

        //Logger
        log.info("Received new script anonymization result for script with id: " + scriptAnonymizationResult.getScriptId());
    }

    public ScriptAnonymizationResult handleScriptAnonymizationResultRequest(long scriptId) {
        return dataHandlerController.getScriptAnonymizationResult(scriptId);
    }
}
