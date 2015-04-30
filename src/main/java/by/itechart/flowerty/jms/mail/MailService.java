package by.itechart.flowerty.jms.mail;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import by.itechart.flowerty.jms.model.Sender;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 *         service for send mail specific contacts.
 */
@Service
public class MailService implements Sender {
    @Autowired
    @Qualifier(value = "jms")
    private JavaMailSenderImpl sender;

    @Autowired
    @Qualifier(value = "smm")
    private SimpleMailMessage simpleMailMessage;

    public void send(String to, String subject, String text) {
	simpleMailMessage.setTo(to);
	simpleMailMessage.setSubject(subject);
	simpleMailMessage.setText(text);
	sender.send(simpleMailMessage);
    }

    public void send(String to, String subject, String text, Map<String, byte[]> resources) throws MessagingException,
	    IOException {
	MimeMessage mimeMessage = sender.createMimeMessage();
	MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
	mimeMessageHelper.setFrom(simpleMailMessage.getFrom());
	mimeMessageHelper.setTo(to);
	mimeMessageHelper.setSubject(subject);
	mimeMessageHelper.setText(text);

	for (String key : resources.keySet()) {
	    InputStreamSource resource = new ByteArrayResource(resources.get(key));
	    mimeMessageHelper.addAttachment(key, resource);
	}
	sender.send(mimeMessage);
    }
}
