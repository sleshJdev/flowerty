package test.by.itechart.flowerty.security;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import test.by.itechart.flowerty.config.aware.WebSecurityConfigurationAware;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Rostislav on 05-May-15
 */

public class SecurityRequestsTest extends WebSecurityConfigurationAware {

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

    @Test
    public void requestProtectedUrlWithSupervisor() throws Exception {
        mvc
                .perform(get("/user/details/1").with(user("supervisor").password("supervisor").roles("SUPERVISOR")))
                .andExpect(status().isForbidden())
                .andExpect(authenticated().withUsername("supervisor"))
        ;
    }

    @Test
    public void requestProtectedUrlWithDeliveryManager() throws Exception {
        mvc
                .perform(get("/user/details/1").with(user("delivery_manager").password("delivery_manager").roles("DELIVERY_MANAGER")))
                .andExpect(status().isForbidden())
                .andExpect(authenticated().withUsername("delivery_manager"))
        ;
    }

    @Test
    public void requestProtectedUrlWithOrdersManager() throws Exception {
        mvc
                .perform(get("/user/details/1").with(user("orders_manager").password("orders_manager").roles("ORDERS_MANAGER")))
                .andExpect(status().isForbidden())
                .andExpect(authenticated().withUsername("orders_manager"))
        ;
    }

    @Test
    public void requestProtectedUrlWithOrdersProcessor() throws Exception {
        mvc
                .perform(get("/user/details/1").with(user("orders_processor").password("orders_processor").roles("ORDERS_PROCESSOR")))
                .andExpect(status().isForbidden())
                .andExpect(authenticated().withUsername("orders_processor"))
        ;
    }

    @Test
    public void requestProtectedUrlWithUserDetails() throws Exception {

        UserDetailsService userDetailsServiceMock = mock(UserDetailsService.class);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "testAdmin", "testAdmin",
                true, true, true, true,
                new ArrayList<GrantedAuthority>() {
                    {
                        add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                    }
                });

        when(userDetailsServiceMock.loadUserByUsername("testAdmin")).thenReturn(userDetails);

        mvc
                .perform(get("/").with(user(userDetailsServiceMock.loadUserByUsername("testAdmin"))))
                .andExpect(status().isOk())
                .andExpect(authenticated().withAuthenticationPrincipal(userDetailsServiceMock.loadUserByUsername("testAdmin")))
        ;

        verify(userDetailsServiceMock, times(2)).loadUserByUsername("testAdmin");
    }

    @Test
    public void requestProtectedUrlWithAuthentication() throws Exception {
        Authentication authentication = new TestingAuthenticationToken("supervisor", "supervisor", "ROLE_SUPERVISOR");
        mvc
                .perform(get("/").with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(authenticated().withAuthentication(authentication))
        ;
    }
}