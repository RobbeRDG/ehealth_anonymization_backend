package be.kul.useraccess.controller.AMQP;

import be.kul.useraccess.Entity.ScriptSummary;
import be.kul.useraccess.Utils.AMQPConfiguration.RabbitMQConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AmqpProducerController {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendAnonymizationRequest(ScriptSummary scriptSummary) throws JsonProcessingException {
        //Convert scriptSummary object to json
        String scriptSummaryString = objectMapper.writeValueAsString(scriptSummary);

        //Send the script summary to the anonymization service
        template.convertAndSend(
                RabbitMQConfig.BRIDGE_EXCHANGE,
                RabbitMQConfig.ANONYMIZATION_REQUEST_BINDING_KEY,
                scriptSummaryString
        );
    }
}
