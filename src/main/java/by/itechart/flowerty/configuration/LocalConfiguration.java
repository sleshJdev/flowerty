package by.itechart.flowerty.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import by.itechart.flowerty.local.settings.Settings;

/**
 * @author Eugene Putsykovich(slesh) Apr 21, 2015
 *
 *         configure local components
 */
@Configuration
@ComponentScan(basePackages = { "by.itechart.flowerty.local" })
@PropertySource("classpath:/local.properties")
public class LocalConfiguration {
    @Autowired
    private Environment environment;
    
    @Bean
    public Settings getSettings() {
	Settings settings = new Settings();
	
	settings.setPicturesPath(environment.getProperty("path.picture"));
	settings.setAttachmentsPath(environment.getProperty("path.attachment"));
	settings.setBirthdayTemplatePath(environment.getProperty("path.birthday.template"));
	settings.setUsFullName(environment.getProperty("us.full.name"));
	
	return settings;
    }
}
