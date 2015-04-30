package by.itechart.flowerty.jms.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.MessagingException;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.jms.mail.MailService;
import by.itechart.flowerty.local.settings.Settings;

/**
 * @author Eugene Putsykovich(slesh) Apr 29, 2015
 *
 *         jms message listener
 */
public class FlowertyMessageListener implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowertyMessageListener.class);

    @Autowired
    private Settings settings;

    @Autowired
    private MailService mailService;

    @Override
    public void onMessage(final Message message) {
	LOGGER.info("recive message: {}", message.getClass());
	if (message instanceof ActiveMQTextMessage) {
	    try {
		final ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
		String to = textMessage.getStringProperty("to");
		String subject = textMessage.getStringProperty("subject");
		String text = textMessage.getStringProperty("text");

		LOGGER.info("text message. to: {}, subject: {}, text: {}", to, subject, text);

		mailService.send(to, subject, text);
	    } catch (JMSException e) {
		e.printStackTrace();
	    }
	} else if (message instanceof ActiveMQBytesMessage) {
	    try {
		final ActiveMQBytesMessage byteMessage = (ActiveMQBytesMessage) message;
		String to = readString(byteMessage);
		String subject = readString(byteMessage);
		String text = readString(byteMessage);

		int quantityAttachments = byteMessage.readInt();
		Map<String, byte[]> attachments = new HashMap<String, byte[]>(quantityAttachments);
		for (int i = 0; i < quantityAttachments; ++i) {
		    String name = readString(byteMessage);
		    byte[] bytes = readBytes(byteMessage);
		    attachments.put(name, bytes);
		}

		LOGGER.info("byte message. to: {}, subject: {}, text: {}, attachments quantity: {}", to, subject, text,
			quantityAttachments);

		mailService.send(to, subject, text, attachments);
	    } catch (IOException | JMSException | MessagingException e) {
		e.printStackTrace();
	    }
	}
    }

    private static final String readString(BytesMessage byteMessage) throws JMSException {
	String data = new String(readBytes(byteMessage));

	return data;
    }

    private static final byte[] readBytes(BytesMessage byteMessage) throws JMSException {
	int length = byteMessage.readInt();
	byte[] buffer = new byte[length];
	byteMessage.readBytes(buffer, length);

	return buffer;
    }
}
