package by.itechart.flowerty.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@ComponentScan(basePackages = { "by.itechart.flowerty" }, includeFilters = @Filter(Controller.class), useDefaultFilters = false)
class WebMvcConfig extends WebMvcConfigurationSupport {

    private static final String VIEWS = "/WEB-INF/views/";

    private static final String RESOURCES_LOCATION = "/resources/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
	RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
	requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
	requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
	return requestMappingHandlerMapping;
    }

    @Bean
    public TemplateResolver templateResolver() {
	TemplateResolver templateResolver = new ServletContextTemplateResolver();
	templateResolver.setPrefix(VIEWS);
	templateResolver.setSuffix(".html");
	templateResolver.setTemplateMode("HTML5");
	templateResolver.setCacheable(false);
	return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	templateEngine.setTemplateResolver(templateResolver());
	templateEngine.addDialect(new SpringSecurityDialect());
	return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
	ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
	thymeleafViewResolver.setTemplateEngine(templateEngine());
	thymeleafViewResolver.setCharacterEncoding("UTF-8");
	return thymeleafViewResolver;
    }

    @Override
    public Validator getValidator() {
	LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
	return validator;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	configurer.enable();
    }
}
