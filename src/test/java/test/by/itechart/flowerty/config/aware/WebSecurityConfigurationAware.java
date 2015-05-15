package test.by.itechart.flowerty.config.aware;

import by.itechart.flowerty.configuration.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

@ContextConfiguration(classes = {
        SearchContext.class,
        SpringSecurityConfiguration.class})
@WebAppConfiguration
public abstract class WebSecurityConfigurationAware extends WebApplicationConfigurationAware {

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected Filter springSecurityFilterChain;

    protected MockMvc mvc;
}
