package test.by.itechart.flowerty.config.aware;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import test.by.itechart.flowerty.config.ApplicationConfiguration;
import test.by.itechart.flowerty.config.EmbeddedDataSourceConfig;
import test.by.itechart.flowerty.config.JpaConfiguration;
import test.by.itechart.flowerty.config.MongoConfiguration;
import by.itechart.flowerty.configuration.LocalConfiguration;
import by.itechart.flowerty.configuration.MailConfiguration;
import by.itechart.flowerty.configuration.SearchContext;
import by.itechart.flowerty.configuration.WebMvcConfiguration;
import by.itechart.flowerty.security.service.UserDetailsServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {
        ApplicationConfiguration.class,
        LocalConfiguration.class,
        MailConfiguration.class,
        EmbeddedDataSourceConfig.class,
        JpaConfiguration.class,
        MongoConfiguration.class,
        SearchContext.class,
        WebMvcConfiguration.class
})
public  class WebApplicationConfigurationAware extends MockTestConfigigurationAware {
    @Inject
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void before() {
        mockMvc = webAppContextSetup(webApplicationContext)
                .build();
    }
    
    public static class MockSecurityContext implements SecurityContext {

        private static final long serialVersionUID = -1386535243513362694L;

        private Authentication authentication;

        public MockSecurityContext(Authentication authentication) {
            this.authentication = authentication;
        }

        @Override
        public Authentication getAuthentication() {
            return this.authentication;
        }

        @Override
        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }
    }
    
    public final UsernamePasswordAuthenticationToken getPrincipal(String username) {

        UserDetails user = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(
                        user, 
                        user.getPassword(), 
                        user.getAuthorities());

        return authentication;
    }
}
