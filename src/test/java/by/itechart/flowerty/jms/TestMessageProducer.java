package by.itechart.flowerty.jms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.itechart.flowerty.configuration.JmsConfiguration;

/**
 * @author Eugene Putsykovich(slesh) Apr 29, 2015
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JmsConfiguration.class })
@Ignore
public class TestMessageProducer {
    @Autowired
    private MessageProducer messageProducer;

    @Test
    public void send_PassMessage_ShoudSendIt() {
	Map<String, String> message = new HashMap<String, String>();
	message.put("from", "eugene");

	messageProducer.send(new MessageCreator() {
	    @Override
	    public Message createMessage(Session session) throws JMSException {
		File data = new File("src/i.png");
		BytesMessage message = null;
		try {
		    message = session.createBytesMessage();
		    message.writeBytes(IOUtils.toByteArray(new FileInputStream(data)));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return message;
	    }
	});
    }
}
