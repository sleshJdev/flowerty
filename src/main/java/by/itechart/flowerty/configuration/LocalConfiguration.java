package by.itechart.flowerty.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import by.itechart.flowerty.local.settings.Settings;

/**
 * @author Eugene Putsykovich(slesh) Apr 21, 2015
 *
 *         configure local components
 */
@Configuration
@ComponentScan(basePackages = { "by.itechart.flowerty.local" })
public class LocalConfiguration {
    @Value("${path.picture}")
    private String picturePath;
    
    @Value("${path.attachment}")
    private String attachmentPath;
    
    @Bean
    public Settings getSettings() {
	Settings settings = new Settings();
	settings.setPicturesPath(picturePath);
	settings.setAttachmentsPath(attachmentPath);
	
	return settings;
    }
}
