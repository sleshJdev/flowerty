package by.itechart.flowerty.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.itechart.flowerty.configuration.JmsConfiguration;
import by.itechart.flowerty.configuration.LocalConfiguration;
import by.itechart.flowerty.configuration.MailConfiguration;
import by.itechart.flowerty.jms.schedule.BirthdayNotifyScheduler;

/**
 * @author Eugene Putsykovich(slesh) May 4, 2015
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MailConfiguration.class, JmsConfiguration.class,
	LocalConfiguration.class })
public class TestBirthdayNotifyScheduler {
    @Autowired
    private BirthdayNotifyScheduler scheduler;

    @Test
    public void test() throws InterruptedException{
	scheduler.start();
	
	Thread.sleep(10_000);
    }
}
