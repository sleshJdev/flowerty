package by.itechart.flowerty.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import by.itechart.flowerty.Application;

@Configuration
@ComponentScan(basePackageClasses = Application.class, includeFilters = @Filter(Controller.class), useDefaultFilters = false)
@PropertySource("classpath:/local.properties")
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private Environment environment;

    private static final String VIEWS = "/WEB-INF/views/";

    private static final String RESOURCES_LOCATION = "/resources/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";

    private static final String PICTURE_HANDLER = "/picture/**";

    @Bean
    public CommonsMultipartResolver multipartResolver() {
	CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	resolver.setMaxUploadSize(100_000_000);// 100mb

	return resolver;
    }

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
	RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
	requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
	requestMappingHandlerMapping.setUseTrailingSlashMatch(false);

	return requestMappingHandlerMapping;
    }

    @Bean
    public ViewResolver viewResolver() {
	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	resolver.setPrefix(VIEWS);
	resolver.setSuffix(".html");
	resolver.setCache(false);

	return resolver;
    }

    @Override
    public Validator getValidator() {
	LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();

	return validator;
    }

    private static String pathResolveHelper(String s) {
	String base = "file:///";
	if (s.charAt(0) == File.separatorChar) {
	    base = base.concat(s.substring(1));
	} else {
	    base = base.concat(s);
	}
	if (s.charAt(s.length() - 1) != File.separatorChar) {
	    base = base.concat("/");
	}
	return base;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
	registry.addResourceHandler(PICTURE_HANDLER).addResourceLocations(
		pathResolveHelper(environment.getProperty("path.picture")));
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	configurer.enable();
    }
}
