package by.itechart.flowerty.security;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import test.by.itechart.flowerty.config.aware.WebSecurityConfigurationAware;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Rostislav on 05-May-15
 */

public class CsrfTests extends WebSecurityConfigurationAware {

    @Autowired
    private CsrfTokenRepository repository;

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
