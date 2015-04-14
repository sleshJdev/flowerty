/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
package by.itechart.flowerty.web.controller.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import by.itechart.flowerty.configuration.ApplicationConfiguration;
import by.itechart.flowerty.configuration.JpaConfiguration;
import by.itechart.flowerty.configuration.WebAppInitializer;
import by.itechart.flowerty.configuration.WebMvcConfiguration;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        ApplicationConfiguration.class,
        WebAppInitializer.class,
        JpaConfiguration.class,
        WebMvcConfiguration.class
})
public class TestMailService{
    
//    @Autowired
//    private MailService mailService;
    
    @Autowired(required = true)
    private JavaMailSender sender; 
    
    @Test
    public void send_PassValidCridentials_ShoudSendMail(){

	//	mailService.send("slesh.eugene93@gmail.com", "test", "hello!");
    }
}
