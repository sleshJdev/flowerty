package by.itechart.flowerty.configuration;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import by.itechart.flowerty.jms.core.FlowertyMessageListener;
import by.itechart.flowerty.jms.core.FlowertyMessagePublisher;

/**
 * @author Eugene Putsykovich(slesh) Apr 29, 2015
 *
 */
@Configuration
@PropertySource("classpath:/jms.properties")
public class JmsConfiguration {
    @Autowired
    private Environment environment;

    @Bean
    public ConnectionFactory connectionFactory() {
	final String username = environment.getProperty("jms.username");
	final String password = environment.getProperty("jms.password");
	final String url = environment.getProperty("jms.broker.url");
	ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(username, password, url);
	ConnectionFactory connectionFactory = new CachingConnectionFactory(activeMQConnectionFactory);

	return connectionFactory;
    }

    @Bean
    public ActiveMQDestination destination() {
	final String queueName = environment.getProperty("jms.queue.name");
	ActiveMQQueue queue = new ActiveMQQueue(queueName);

	return queue;
    }

    // @Bean
    // public SimpleMessageListenerContainer simpleMessageListenerContainer() {
    // SimpleMessageListenerContainer listenerContainer = new
    // SimpleMessageListenerContainer();
    // listenerContainer.setConnectionFactory(connectionFactory());
    // listenerContainer.setDestination(destination());
    // listenerContainer.setMessageListener(messageListener());
    //
    // return listenerContainer;
    // }

    @Bean
    public FlowertyMessageListener messageListener() {
	return new FlowertyMessageListener();
    }

    @Bean
    public FlowertyMessagePublisher messageProducer() {
	JmsTemplate jmsTemplate = new JmsTemplate();
	jmsTemplate.setConnectionFactory(connectionFactory());
	jmsTemplate.setDefaultDestination(destination());

	FlowertyMessagePublisher messageProducer = new FlowertyMessagePublisher(jmsTemplate);

	return messageProducer;
    }
}
