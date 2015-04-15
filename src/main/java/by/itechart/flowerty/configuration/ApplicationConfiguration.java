package by.itechart.flowerty.configuration;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import by.itechart.flowerty.Application;
import by.itechart.flowerty.local.listener.StartContextApplicationListener;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Configuration for fetch properties from resources
 */
@Configuration
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @ComponentScan.Filter({ Controller.class, Configuration.class }))
public class ApplicationConfiguration {
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocations(new ClassPathResource("/persistence.properties"));

        return ppc;
    }

    @Bean
    public ApplicationListener<ContextStartedEvent> eventListenerBean(){
        //TODO: current version of listener not work. need improve..,
        return new StartContextApplicationListener();
    }
}