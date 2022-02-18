package be.kul.useraccess.Utils.AMQPConfiguration;

import be.kul.useraccess.controller.AMQP.AmqpConsumerController;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class RabbitMQConfig {
    public static final String BRIDGE_EXCHANGE = "bridgeExchange";
    public static final String ANONYMIZATION_REQUEST_QUEUE = "anonymizationRequestQueue";
    public static final String ANONYMIZATION_REQUEST_BINDING_KEY = "anonymization.request.*";
    public static final String ANONYMIZATION_RESULT_QUEUE = "anonymizationResultQueue";
    public static final String ANONYMIZATION_RESULT_BINDING_KEY = "anonymization.result.*";


    @Resource(name="bridgeRabbitAdmin")
    private RabbitAdmin bridgeRabbitAdmin;

/*
    @Bean
    public Declarables RabbitInit() {
        //Create topic exchange
        TopicExchange bridgeExchange = new TopicExchange(BRIDGE_EXCHANGE);

        //Build the queues
        Queue anonymizationRequestQueue = QueueBuilder
                .durable(ANONYMIZATION_REQUEST_QUEUE)
                .build();
        Queue anonymizationResultQueue = QueueBuilder
                .durable(ANONYMIZATION_RESULT_QUEUE)
                .build();

        return new Declarables(
                bridgeExchange,
                anonymizationRequestQueue,
                anonymizationResultQueue,
                BindingBuilder.bind(anonymizationRequestQueue)
                        .to(bridgeExchange)
                        .with(ANONYMIZATION_REQUEST_BINDING_KEY),
                BindingBuilder.bind(anonymizationResultQueue)
                        .to(bridgeExchange)
                        .with(ANONYMIZATION_RESULT_BINDING_KEY)

        );
    }

 */

    @Bean
    Queue anonymizationRequestQueue() {
        return QueueBuilder
                .durable(ANONYMIZATION_REQUEST_QUEUE)
                .build();
    }

    @Bean
    Queue anonymizationResultQueue() {
        return QueueBuilder
                .durable(ANONYMIZATION_RESULT_QUEUE)
                .build();
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(BRIDGE_EXCHANGE);
    }

    @Bean
    Binding anonymizationRequestQueueBinding(Queue anonymizationRequestQueue, TopicExchange exchange) {
        return BindingBuilder.bind(anonymizationRequestQueue)
                .to(exchange)
                .with(ANONYMIZATION_REQUEST_BINDING_KEY);
    }

    @Bean
    Binding anonymizationResultQueueBinding(Queue anonymizationResultQueue, TopicExchange exchange) {
        return BindingBuilder.bind(anonymizationResultQueue)
                .to(exchange)
                .with(ANONYMIZATION_RESULT_BINDING_KEY);
    }

}
