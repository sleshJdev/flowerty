package by.itechart.flowerty.security;

import by.itechart.flowerty.configuration.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Rostislav on 04-May-15
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@TestExecutionListeners(listeners = {ServletTestExecutionListener.class,
//        DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
//        WithSecurityContextTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        WebMvcConfiguration.class,
        ApplicationConfiguration.class,
        JpaConfiguration.class,
        SearchContext.class,
        SpringSecurityConfiguration.class})
public class ITTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

//    @Test
//    public void getAccount() throws Exception {
//        this.mockMvc.perform(get("/accounts/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$.name").value("Lee"));
//    }
//
//    @Test
//    public void loginWithIncorrectCredentials() throws Exception {
//        mockMvc.perform(post("/api/login")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("username", "user1")
//                        .param("password", "password1")
//        )
//                .andExpect(status().isUnauthorized());
//    }

    @Test
    public void getUser() throws Exception {
        this.mockMvc.perform(get("/user/details/1"))
                .andExpect(status().isOk());
    }

}
