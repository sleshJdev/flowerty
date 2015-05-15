/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 */
package by.itechart.flowerty.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import by.itechart.flowerty.Application;

/**
 * @author Eugene Putsykovich(slesh) Apr 14, 2015
 *
 *         mailing configuration
 */
@Configuration
@EnableAsync
@EnableScheduling
@ComponentScan(basePackageClasses = Application.class)
@PropertySource("classpath:/web.properties")
public class MailConfiguration {
    @Autowired
    private Environment environment;

    private Properties getSsl() {
	final Properties SSL = new Properties();
	SSL.put("mail.smtp.host", environment.getProperty("ssl.mail.smtp.host"));
	SSL.put("mail.smtp.socketFactory.port", environment.getProperty("ssl.mail.smtp.socketFactory.port"));
	SSL.put("mail.smtp.socketFactory.class", environment.getProperty("ssl.mail.smtp.socketFactory.class"));
	SSL.put("mail.smtp.auth", environment.getProperty("ssl.mail.smtp.auth"));
	SSL.put("mail.smtp.port", environment.getProperty("ssl.mail.smtp.port"));

	return SSL;
    }

    private Properties getTls() {
	final Properties TLS = new Properties();
	TLS.put("mail.smtp.auth", environment.getProperty("tls.mail.smtp.auth"));
	TLS.put("mail.smtp.starttls.enable", environment.getProperty("tls.mail.smtp.starttls.enable"));
	TLS.put("mail.smtp.host", environment.getProperty("tls.mail.smtp.host"));
	TLS.put("mail.smtp.port", environment.getProperty("tls.mail.smtp.port"));

	return TLS;
    }

    @Bean(name = "jms")
    public JavaMailSenderImpl javaMailSender() {
	JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

	String security = environment.getProperty("mail.security.type");
	javaMailSender.setJavaMailProperties("ssl".equalsIgnoreCase(security.trim()) ? getSsl() : getTls());

	javaMailSender.setPassword(environment.getProperty("mail.password"));
	javaMailSender.setUsername(environment.getProperty("mail.username"));

	return javaMailSender;
    }

    @Bean(name = "smm")
    public SimpleMailMessage simpleMailMessage() {
	SimpleMailMessage message = new SimpleMailMessage();
	message.setFrom(environment.getProperty("mail.from"));

	return message;
    }
}
