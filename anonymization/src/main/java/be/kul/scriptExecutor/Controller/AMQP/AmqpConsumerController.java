package be.kul.scriptExecutor.Controller.AMQP;

import be.kul.scriptExecutor.Service.ScriptExecutorService;
import be.kul.scriptExecutor.Utils.AMQPConfiguration.RabbitMQConfig;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ScriptSummary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

public class AmqpConsumerController {
    @Autowired
    private ScriptExecutorService scriptExecutorService;

    @Autowired
    private ObjectMapper objectMapper;

    public void onAnonymizationRequest(@Payload String scriptSummaryString) throws JsonProcessingException {
        //get the scriptExecutionResult form the string
        ScriptSummary scriptSummary = objectMapper.readValue(scriptSummaryString, ScriptSummary.class);

        //Handle the script execution result
        scriptExecutorService.handleAnonymizationRequest(scriptSummary);
    }
}
