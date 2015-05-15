package by.itechart.flowerty.jms.core;

import java.io.IOException;
import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author Eugene Putsykovich(slesh) Apr 28, 2015
 * 
 *         sends messages to the queue
 */

public class FlowertyMessagePublisher implements Sender {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowertyMessagePublisher.class);
    
    private final JmsTemplate jmsTemplate;

    public FlowertyMessagePublisher(JmsTemplate jmsTemplate) {
	this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void send(final String to, final String subject, final String text) {
	send(new MessageCreator() {
	    @Override
	    public Message createMessage(Session session) throws JMSException {
		TextMessage textMessage = session.createTextMessage();
		textMessage.setStringProperty("to", to);
		textMessage.setStringProperty("subject", subject);
		textMessage.setStringProperty("text", text);

		LOGGER.info("send text message. to: {}, subject: {}, text: {}", to, subject, text);
		
		return textMessage;
	    }
	});
    }

    @Override
    public void send(final String to, final String subject, final String text, final Map<String, byte[]> resources)
	    throws MessagingException, IOException {
	send(new MessageCreator() {
	    @Override
	    public Message createMessage(Session session) throws JMSException {
		BytesMessage bytesMessage = session.createBytesMessage();
		
		bytesMessage.writeInt(to.getBytes().length);//1 part: quantity bytes per email of receiver 
		bytesMessage.writeBytes(to.getBytes());//2 part: receiver address data

		bytesMessage.writeInt(subject.getBytes().length);//3 part: quantity bytes per subject
		bytesMessage.writeBytes(subject.getBytes());//4 part: subject data

		bytesMessage.writeInt(text.getBytes().length);//5 part: quantity bytes per message body		
		bytesMessage.writeBytes(text.getBytes());//6 part:  text data

		bytesMessage.writeInt(resources.size());//7 part: quantity bytes per number of attachments
		
		//8... parts: attachments(name and value)
		byte[] buffer = null;
		for (String key : resources.keySet()) {
		    //name
		    buffer = key.getBytes();
		    bytesMessage.writeInt(buffer.length);
		    bytesMessage.writeBytes(buffer);
		    //attachment
		    buffer = resources.get(key);
		    bytesMessage.writeInt(buffer.length);
		    bytesMessage.writeBytes(buffer);
		}
		
		LOGGER.info("send byte message. to: {}, subject: {}, text: {}, attachments quantity: {}", to, subject,
			text, resources.size());

		return bytesMessage;
	    }
	});
    }

    public void send(MessageCreator message) {
	jmsTemplate.send(message);
    }
}
