package be.kul.scriptExecutor.Utils.AMQPConfiguration;

import be.kul.scriptExecutor.Controller.AMQP.AmqpConsumerController;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

@Configuration
public class RabbitMQConfig {
    public static final String BRIDGE_EXCHANGE = "bridgeExchange";
    public static final String ANONYMIZATION_REQUEST_QUEUE = "anonymizationRequestQueue";
    public static final String ANONYMIZATION_REQUEST_BINDING_KEY = "anonymization.request.*";
    public static final String ANONYMIZATION_RESULT_QUEUE = "anonymizationResultQueue";
    public static final String ANONYMIZATION_RESULT_BINDING_KEY = "anonymization.result.*";

    @Value("${spring.rabbitmq.host}")
    private String RABBITMQ_BRIDGE_CONNECTION_HOST;

    @Value("${spring.rabbitmq.username}")
    private String RABBITMQ_BRIDGE_CONNECTION_USERNAME;

    @Value("${spring.rabbitmq.password}")
    private String RABBITMQ_BRIDGE_CONNECTION_PASSWORD;

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(bridgeConnectionFactory());
    }

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

    @Bean(name = "bridgeConnectionFactory")
    @Primary
    public CachingConnectionFactory bridgeConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(RABBITMQ_BRIDGE_CONNECTION_HOST);
        cachingConnectionFactory.setUsername(RABBITMQ_BRIDGE_CONNECTION_USERNAME);
        cachingConnectionFactory.setPassword(RABBITMQ_BRIDGE_CONNECTION_PASSWORD);
        return cachingConnectionFactory;
    }

    @Bean
    SimpleMessageListenerContainer consumerListenerContainer(ConnectionFactory connectionFactory, @Qualifier("consumerListenerAdapter") MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(anonymizationRequestQueue());
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter consumerListenerAdapter(AmqpConsumerController amqpConsumerController) {
        return new MessageListenerAdapter(amqpConsumerController, "onAnonymizationRequest");
    }

    @Bean
    AmqpConsumerController amqpConsumerController() {
        return new AmqpConsumerController();
    }

    @Bean(name = "bridgeRabbitTemplate")
    @Primary
    public RabbitTemplate bridgeRabbitTemplate() {
        return new RabbitTemplate(bridgeConnectionFactory());
    }

    @Bean(name = "bridgeRabbitAdmin")
    public RabbitAdmin bridgeRabbitAdmin() {
        return new RabbitAdmin(bridgeConnectionFactory());
    }

    @Bean(name = "jsonMessageConverter")
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
