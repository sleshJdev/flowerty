package by.itechart.flowerty.security;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import test.by.itechart.flowerty.config.aware.WebSecurityConfigurationAware;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Rostislav on 10-May-15
 */

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
public class TestTemp extends WebSecurityConfigurationAware {

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void requestProtectedUrlWithUser() throws Exception {
        mvc
                .perform(get("/").with(user("user")))
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("user"))
        ;
    }
}
