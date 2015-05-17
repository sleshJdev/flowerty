package test.by.itechart.flowerty.config.aware;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.security.Principal;
import java.util.Collection;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = { ApplicationConfiguration.class, LocalConfiguration.class, MailConfiguration.class,
	EmbeddedDataSourceConfig.class, JpaConfiguration.class, MongoConfiguration.class, SearchContext.class,
	WebMvcConfiguration.class })
public class WebApplicationConfigurationAware extends MockTestConfigigurationAware {
    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Before
    public void before() {
	mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    public MockHttpSession makeAuthSession(String username) {
	MockHttpSession session = new MockHttpSession();
	session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
		SecurityContextHolder.getContext());
	UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	Principal principal = new NamedAuthPrincipal(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
	Authentication authToken = new UsernamePasswordAuthenticationToken(principal, null, userDetails.getAuthorities());
	SecurityContextHolder.getContext().setAuthentication(authToken);

	return session;
    }

    public static class NamedAuthPrincipal implements Principal, UserDetails {
	private static final long serialVersionUID = 423725604321483049L;

	private String name;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public NamedAuthPrincipal(String name, String password, Collection<? extends GrantedAuthority> authorities) {
	    this.name = name;
	    this.password = password;
	    this.authorities = authorities;
	}

	@Override
	public String getName() {
	    return name;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	}

	@Override
	public String getPassword() {
	    return password;
	}

	@Override
	public String getUsername() {
	    return name;
	}

	@Override
	public boolean isAccountNonExpired() {
	    return false;
	}

	@Override
	public boolean isAccountNonLocked() {
	    return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return false;
	}

	@Override
	public boolean isEnabled() {
	    return false;
	}
    }
}
