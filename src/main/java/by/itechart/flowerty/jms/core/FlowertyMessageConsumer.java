package by.itechart.flowerty.jms.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * @author Eugene Putsykovich(slesh) Apr 30, 2015
 *
 *         listening to the message queue
 */
@Component
public class FlowertyMessageConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowertyMessageConsumer.class);

    private ConnectionFactory connectionFactory;
    private Destination destination;
    private MessageListener messageListener;
    private Connection connection;

    private boolean isStart = false;

    @Autowired
    public FlowertyMessageConsumer(ConnectionFactory connectionFactory, Destination destination,
	    MessageListener messageListener) throws JMSException {
	this.connectionFactory = connectionFactory;
	this.destination = destination;
	this.messageListener = messageListener;
    }

    public void start() throws JMSException {
	if (!isStart) {
	    LOGGER.info("start messaging...");

	    connection = connectionFactory.createConnection();
	    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    MessageConsumer consumer = session.createConsumer(destination);
	    consumer.setMessageListener(messageListener);
	    connection.start();
	    isStart = true;
	}
    }

    public void stop() throws JMSException {
	if (isStart) {
	    if (connection != null) {
		LOGGER.info("stop messagin");

		connection.close();
		connection = null;

		isStart = false;
	    }
	}
    }
}
