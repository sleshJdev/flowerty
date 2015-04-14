/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
package by.itechart.flowerty.web.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSender sender; 
    
    public void send(String to, String subject, String text){
	SimpleMailMessage message = new SimpleMailMessage();
	message.setTo(to);
	message.setSubject(subject);
	message.setText(text);
	
	sender.send(message);
    }
}
