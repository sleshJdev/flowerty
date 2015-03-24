package by.itechart.flowerty.configuration;

import static org.springframework.context.annotation.ComponentScan.Filter;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import by.itechart.flowerty.Application;

@Configuration
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @Filter({ Controller.class, Configuration.class }))
public class ApplicationConfiguration {
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
	PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
	ppc.setLocation(new ClassPathResource("/persistence.properties"));

	return ppc;
    }

}