package by.itechart.flowerty.jms;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQTextMessage;

/**
 * @author Eugene Putsykovich(slesh) Apr 29, 2015
 *
 *         jms message listener
 */
public class MessageReceiver implements MessageListener {
    @Override
    public void onMessage(final Message message) {
	System.out.println("recive message");
	if (message instanceof ActiveMQTextMessage) {
	    final ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
	    try {
		System.out.println(textMessage.getText());
	    } catch (JMSException e) {
		e.printStackTrace();
	    }
	} else if (message instanceof ActiveMQMapMessage) {
	    final ActiveMQMapMessage mapMessage = (ActiveMQMapMessage) message;
	    try {
		Enumeration<?> keys = mapMessage.getPropertyNames();
		while (keys.hasMoreElements()) {
		    Object key = keys.nextElement();
		    System.out.println(mapMessage.getProperty(key.toString()));
		}
	    } catch (JMSException | IOException e) {
		e.printStackTrace();
	    }
	} else if (message instanceof ActiveMQBytesMessage) {
	    final ActiveMQBytesMessage byteMessage = (ActiveMQBytesMessage) message;
	    try {
		File file = new File("src/i1.png");
		if (!file.exists()) {
		    file.createNewFile();
		}
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		byte[] bytes = new byte[2048];
		int quantity = 0;
		while ((quantity = (byteMessage.readBytes(bytes))) != -1) {
		    out.write(bytes, 0, quantity);
		}
		out.close();

	    } catch (IOException | JMSException e) {
		e.printStackTrace();
	    }
	}
    }
}
