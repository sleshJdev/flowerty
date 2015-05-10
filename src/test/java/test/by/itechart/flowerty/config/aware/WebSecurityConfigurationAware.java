package test.by.itechart.flowerty.config.aware;

import org.junit.Before;
import org.springframework.security.web.FilterChainProxy;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public abstract class WebSecurityConfigurationAware extends WebApplicationConfigurationAware {

    @Inject
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext)
                .addFilters(this.springSecurityFilterChain).build();
    }
}
