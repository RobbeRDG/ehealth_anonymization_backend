package be.kul.useraccess.controller.AMQP;

import be.kul.useraccess.Entity.ScriptAnonymizationResult;
import be.kul.useraccess.Service.UserAccessService;
import be.kul.useraccess.Utils.AMQPConfiguration.RabbitMQConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

public class AmqpConsumerController {
    @Autowired
    private UserAccessService userAccessService;

    @Autowired
    private ObjectMapper objectMapper;

    public void onAnonymizationResult(@Payload String scriptExecutionResultString) throws JsonProcessingException {
        //get the scriptExecutionResult form the string
        ScriptAnonymizationResult scriptAnonymizationResult = objectMapper.readValue(scriptExecutionResultString, ScriptAnonymizationResult.class);

        //Handle the script execution result
        userAccessService.handleScriptExecutionResult(scriptAnonymizationResult);
    }
}
