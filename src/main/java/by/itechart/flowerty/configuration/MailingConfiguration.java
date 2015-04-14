/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
package by.itechart.flowerty.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
@Configuration 
public class MailingConfiguration {

    @Value("${mail.transport.protocol}")
    private String protocol;
    
    @Value("${mail.smtp.auth}")
    private String isAuth;

    @Value("${mail.smtp.starttls.enable}")
    private String isStarttls;
    
    @Value("${mail.debug}")
    private String isDebug;
    
    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private Integer port;
    
    @Value("${username}")
    private String username;

    @Value("password")
    private String password;
    
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol(protocol);
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        return javaMailSender;
    }
}