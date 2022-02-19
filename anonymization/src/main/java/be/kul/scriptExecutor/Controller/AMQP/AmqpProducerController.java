package be.kul.scriptExecutor.Controller.AMQP;

import be.kul.scriptExecutor.Utils.AMQPConfiguration.RabbitMQConfig;
import be.kul.scriptExecutor.Utils.ScriptAnonymizationResult.ScriptAnonymizationResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpProducerController {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendAnonymizationResult(ScriptAnonymizationResult scriptAnonymizationResult) throws JsonProcessingException {
        //Convert scriptSummary object to json
        String scriptExecutionResultString = objectMapper.writeValueAsString(scriptAnonymizationResult);

        //Send the script summary to the anonymization service
        template.convertAndSend(
                RabbitMQConfig.BRIDGE_EXCHANGE,
                RabbitMQConfig.ANONYMIZATION_RESULT_QUEUE,
                scriptExecutionResultString
        );
    }
}
