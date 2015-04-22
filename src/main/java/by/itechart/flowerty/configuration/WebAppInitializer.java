package by.itechart.flowerty.configuration;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         configuration for servlets
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected String[] getServletMappings() {
	return new String[] { "/" };
    }
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
	return new Class<?>[] { ApplicationConfiguration.class, MailConfiguration.class, JpaConfiguration.class,
		SpringSecurityConfiguration.class, SearchContext.class, LocalConfiguration.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
	return new Class<?>[] { WebMvcConfiguration.class };
    }

    @Override
    protected Filter[] getServletFilters() {
	CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	characterEncodingFilter.setEncoding("UTF-8");
	characterEncodingFilter.setForceEncoding(true);

	DelegatingFilterProxy securityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");
	

	return new Filter[] { characterEncodingFilter, securityFilterChain };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
	registration.setInitParameter("defaultHtmlEscape", "true");
	registration.setInitParameter("spring.profiles.active", "default");
    }
}