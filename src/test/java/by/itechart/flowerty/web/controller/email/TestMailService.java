/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
package by.itechart.flowerty.web.controller.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.itechart.flowerty.configuration.MailConfiguration;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MailConfiguration.class)
public class TestMailService {
    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    SimpleMailMessage message;

    @Test
    public void send_PassValidEmail_ShoudSendEmail() {
	message.setTo("studentbntu@mail.ru");
	message.setSubject("test");
	message.setText("hello!");
	javaMailSender.send(message);
	//TODO: how assertion use?
    }
}
