package by.itechart.flowerty.configuration;

import by.itechart.flowerty.security.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Created by Rostislav on 26-Mar-15.
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

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    private Logger LOGGER = LoggerFactory.getLogger(SpringSecurityConfiguration.class);

    @Bean
    public RememberMeServices rememberMeServices(String internalSecretKey) {
        TokenBasedRememberMeServices services = new TokenBasedRememberMeServices(internalSecretKey, userDetailsService, LOGGER);
        services.setAlwaysRemember(true);
        return services;
    }

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
        http
//                .exceptionHandling()
//                .authenticationEntryPoint(unauthorizedHandler)
//            .and()
                .authorizeRequests()
                .antMatchers("/user/list/**")
                .access("hasRole('ROLE_ADMIN')")
            .and()
                .rememberMe()
                .rememberMeServices(rememberMeServices("key")).key("key")
        .and()
                .formLogin()
                .loginPage("/#/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/login")
//                .successHandler(authSuccess)
                .failureHandler(authFailure)
            .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
            .and()
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .csrf().csrfTokenRepository(csrfTokenRepository())
        ;
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}