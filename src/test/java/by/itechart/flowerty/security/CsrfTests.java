package by.itechart.flowerty.security;

import by.itechart.flowerty.configuration.*;
import by.itechart.flowerty.security.handler.AccessDeniedHandler;
import by.itechart.flowerty.security.handler.AuthFailure;
import by.itechart.flowerty.security.handler.AuthSuccess;
import by.itechart.flowerty.security.handler.LogoutSuccessHandlerImpl;
import by.itechart.flowerty.security.service.UserDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Rostislav on 05-May-15
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
//        WebMvcConfiguration.class,
//        ApplicationConfiguration.class,
//        JpaConfiguration.class,
        SearchContext.class,
        SpringSecurityConfiguration.class,
        AuthFailure.class,
        AuthSuccess.class,
        LogoutSuccessHandlerImpl.class,
        AccessDeniedHandler.class,
        UserDetailsServiceImpl.class})
@WebAppConfiguration
public class CsrfTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private CsrfTokenRepository repository;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .defaultRequest(get("/"))
                .addFilters(springSecurityFilterChain)
                .build()
        ;
    }

    @Test
    public void postWithoutCsrfWorks() throws Exception {
        mvc
                .perform(post("/"))
                .andExpect(status().isForbidden())
        ;
    }

    @Test
    public void postWithCsrfWorks() throws Exception {
        mvc
                .perform(post("/").with(csrf()))
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void postWithCsrfWorksWithPut() throws Exception {
        mvc
                .perform(put("/").with(csrf()))
                .andExpect(status().isOk())
        ;
    }
}
