package by.itechart.flowerty.web.controller.email;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 *         service for send mail specific contacts.
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSenderImpl sender;

    @Autowired
    private SimpleMailMessage simpleMailMessage;

    public void send(String to, String subject, String text) {
	simpleMailMessage.setTo(to);
	simpleMailMessage.setSubject(subject);
	simpleMailMessage.setText(text);
	sender.send(simpleMailMessage);
    }
    
    public void send(String to, String subject, String text, MultipartFile[] attachments) throws MessagingException, IOException {
	MimeMessage mimeMessage = sender.createMimeMessage();
	MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
	mimeMessageHelper.setFrom(simpleMailMessage.getFrom());
	mimeMessageHelper.setTo(to);
	mimeMessageHelper.setSubject(to);
	mimeMessageHelper.setText(text);
	for (MultipartFile attachment : attachments) {
	    InputStreamSource resource = new ByteArrayResource(IOUtils.toByteArray(attachment.getInputStream()));
	    mimeMessageHelper.addAttachment(attachment.getOriginalFilename(), resource);
	}
	sender.send(mimeMessage);
    }
}
