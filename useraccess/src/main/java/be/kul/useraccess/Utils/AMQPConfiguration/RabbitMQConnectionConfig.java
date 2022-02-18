package be.kul.useraccess.Utils.AMQPConfiguration;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConnectionConfig {
    @Value("${spring.rabbitmq.host}")
    private String RABBITMQ_BRIDGE_CONNECTION_HOST;

    @Value("${spring.rabbitmq.username}")
    private String RABBITMQ_BRIDGE_CONNECTION_USERNAME;

    @Value("${spring.rabbitmq.password}")
    private String RABBITMQ_BRIDGE_CONNECTION_PASSWORD;


    @Bean(name = "bridgeConnectionFactory")
    @Primary
    public CachingConnectionFactory internalConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(RABBITMQ_BRIDGE_CONNECTION_HOST);
        cachingConnectionFactory.setUsername(RABBITMQ_BRIDGE_CONNECTION_USERNAME);
        cachingConnectionFactory.setPassword(RABBITMQ_BRIDGE_CONNECTION_PASSWORD);
        return cachingConnectionFactory;
    }

    @Bean(name = "bridgeRabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory internalRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(internalConnectionFactory());
        return factory;
    }

    @Bean(name = "bridgeRabbitTemplate")
    @Primary
    public RabbitTemplate internalRabbitTemplate() {
        return new RabbitTemplate(internalConnectionFactory());
    }

    @Bean(name = "bridgeRabbitAdmin")
    public RabbitAdmin internalRabbitAdmin() {
        return new RabbitAdmin(internalConnectionFactory());
    }

    @Bean(name = "jsonMessageConverter")
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
