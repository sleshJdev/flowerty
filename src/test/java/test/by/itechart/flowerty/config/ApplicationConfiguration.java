package test.by.itechart.flowerty.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *         
 *         configuration for fetch properties from resources
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocations(new ClassPathResource("/hsql.properties"), new ClassPathResource("/mongo.properties"));

        return ppc;
    }
}
