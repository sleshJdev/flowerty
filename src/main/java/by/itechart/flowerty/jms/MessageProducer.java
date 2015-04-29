package by.itechart.flowerty.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author Eugene Putsykovich(slesh) Apr 28, 2015
 * 
 *         sends messages to the queue
 */

public class MessageProducer {
    private final JmsTemplate jmsTemplate;

    public void send(MessageCreator message){
	jmsTemplate.send(message);
    }

    public MessageProducer(JmsTemplate jmsTemplate) {
	this.jmsTemplate = jmsTemplate;
    }
}
