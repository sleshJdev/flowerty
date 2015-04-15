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

    public void send(EmailInfo emailInfo) {
	if(emailInfo == null){
	    throw new IllegalArgumentException("emailInfo is null");
	}
	simpleMailMessage.setTo(emailInfo.getTo());
	simpleMailMessage.setSubject(emailInfo.getSubject());
	simpleMailMessage.setText(emailInfo.getText());
	sender.send(simpleMailMessage);
    }

    public void send(EmailInfo emailInfo, MultipartFile[] attachments) throws MessagingException, IOException {
	if(attachments == null){
	    throw new IllegalArgumentException("attachments is null");
	}
	if(emailInfo == null){
	    throw new IllegalArgumentException("emailInfo is null");
	}
	MimeMessage mimeMessage = sender.createMimeMessage();
	MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
	mimeMessageHelper.setFrom(simpleMailMessage.getFrom());
	mimeMessageHelper.setTo(emailInfo.getTo());
	mimeMessageHelper.setSubject(emailInfo.getSubject());
	mimeMessageHelper.setText(emailInfo.getText());
	for (MultipartFile attachment : attachments) {
	    InputStreamSource resource = new ByteArrayResource(IOUtils.toByteArray(attachment.getInputStream()));
	    mimeMessageHelper.addAttachment(attachment.getOriginalFilename(), resource);
	}
	sender.send(mimeMessage);
    }
}
