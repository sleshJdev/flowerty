package by.itechart.flowerty.local.listener;

import javax.jms.JMSException;
import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import by.itechart.flowerty.jms.core.FlowertyMessageConsumer;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         listen start of context application is started
 */
@Component
public class StartContextApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private Logger LOGGER = LoggerFactory.getLogger(StartContextApplicationListener.class);

    @Autowired
    private FlowertyMessageConsumer consumer;
 
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
	LOGGER.info("context refreshed");

	try {
	    consumer.start();
	} catch (JMSException e) {
	    LOGGER.error(e.getMessage());
	    throw new RuntimeErrorException(new Error(
		    "cannot initialize jsm components. maybe you dont run activemq server."));
	}
    }
}
