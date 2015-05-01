package by.itechart.flowerty.local.listener;

import javax.jms.JMSException;
import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import by.itechart.flowerty.jms.core.FlowertyMessageConsumer;

/**
 * @author Eugene Putsykovich(slesh) May 1, 2015
 * 
 *         listen start of context application is started
 */
@Component
public class StopContextApplicationListener implements ApplicationListener<ContextClosedEvent> {
    private Logger LOGGER = LoggerFactory.getLogger(StartContextApplicationListener.class);

    @Autowired
    private FlowertyMessageConsumer consumer;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
	LOGGER.info("closed context");

	try {
	    consumer.stop();
	} catch (JMSException e) {
	    LOGGER.error(e.getMessage());
	    throw new RuntimeErrorException(new Error("cannot stop jms components."));
	}
    }
}
