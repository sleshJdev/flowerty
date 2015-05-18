package test.by.itechart.flowerty.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import by.itechart.flowerty.configuration.ApplicationConfiguration;
import by.itechart.flowerty.configuration.JpaConfiguration;
import by.itechart.flowerty.configuration.SearchContext;
import by.itechart.flowerty.configuration.SpringSecurityConfiguration;
import by.itechart.flowerty.configuration.WebMvcConfiguration;

/**
 * Created by Rostislav on 05-May-15
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        WebMvcConfiguration.class,
        ApplicationConfiguration.class,
        JpaConfiguration.class,
        SearchContext.class,
        SpringSecurityConfiguration.class})
@WebAppConfiguration
public class LoginRequestBuilderAuthenticationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @SuppressWarnings("deprecation")
    public void authenticationSuccess() throws Exception {
        mvc
                .perform(login().user("test").password("test"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/login"))
                .andExpect(authenticated())
        ;
    }

    @Test
    public void authenticationFailed() throws Exception {
        mvc
                .perform(login().user("incorrect").password("incorrect"))
                .andExpect(status().isUnauthorized())
                .andExpect(redirectedUrl(null))
                .andExpect(unauthenticated())
        ;
    }

    static FormLoginRequestBuilder login() {
        return SecurityMockMvcRequestBuilders
                .formLogin("/login")
                .userParameter("username")
                .passwordParam("password")
                ;
    }

    @Test
    public void authenticationSuccess1() throws Exception {
        mvc
                .perform(formLogin("/login").user("test"))
                .andExpect(status().isUnauthorized())
                .andExpect(redirectedUrl(null))
                .andExpect(unauthenticated())
                ;
    }
}