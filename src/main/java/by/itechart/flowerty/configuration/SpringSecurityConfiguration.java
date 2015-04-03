package by.itechart.flowerty.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import by.itechart.flowerty.security.AuthFailure;
import by.itechart.flowerty.security.AuthSuccess;
import by.itechart.flowerty.security.CustomAuthenticationProvider;
import by.itechart.flowerty.security.EntryPointUnauthorizedHandler;

/**
 * Created by Rostislav on 26-Mar-15.)
 */

@Configuration
@EnableWebMvcSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthFailure authFailure;
    
    @Autowired
    private AuthSuccess authSuccess;

    @Autowired
    private EntryPointUnauthorizedHandler unauthorizedHandler;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticator());
    }

    @Bean
    public AuthenticationProvider authenticator() {
        return new CustomAuthenticationProvider();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        http
	        .authorizeRequests()
	        .antMatchers("/user/list/**")
	        .access("hasRole('ROLE_ADMIN')")
	    .and()
	        .formLogin()
	        .loginPage("/login")
	        .loginProcessingUrl("/authenticate")
	        .successHandler(authSuccess)
	        .defaultSuccessUrl("/", false)
	    .and()
	        .logout()
	        .logoutSuccessUrl("/login?logout");
    }
}