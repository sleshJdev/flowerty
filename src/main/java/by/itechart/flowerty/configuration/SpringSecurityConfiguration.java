package by.itechart.flowerty.configuration;

import by.itechart.flowerty.security.CsrfHeaderFilter;
import by.itechart.flowerty.security.CustomAuthenticationProvider;
import by.itechart.flowerty.security.TokenBasedRememberMeServices;
import by.itechart.flowerty.security.handler.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Rostislav on 26-Mar-15
 */
@Configuration
@EnableWebMvcSecurity
@EnableWebMvc
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthFailure authFailure;

    @Autowired
    private AuthSuccess authSuccess;

    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticator());
    }

    @Bean
    public AuthenticationProvider authenticator() {
        return new CustomAuthenticationProvider();
    }

    @Transactional
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/**")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/profile")
                .authenticated()
                .antMatchers("/contact/**")
                .access("hasAnyRole('ROLE_SUPERVISOR', 'ROLE_ORDERS_MANAGER')")
            .and()
                .rememberMe()
                .rememberMeServices(rememberMeServices())
                .key(KEY)
            .and()
                .formLogin()
                .loginPage("/#/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/login")
//                .successHandler(authSuccess)
                .failureHandler(authFailure)
                .permitAll()
            .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
            .and()
                .csrf()
                .csrfTokenRepository(csrfTokenRepository())
//            .and()
//                .exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler)
            .and()
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
        ;
    }


//    @Bean(name="simpleMappingExceptionResolver")
//    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
//
//        Properties mappings = new Properties();
//        mappings.setProperty(".DataAccessException", "dataAccessFailure");
//        mappings.setProperty(".NoSuchRequestHandlingMethodException", "resourceNotFound");
//        mappings.setProperty(".TypeMismatchException", "resourceNotFound");
//        mappings.setProperty(".MissingServletRequestParameterException", "resourceNotFound");
//
//        r.setDefaultErrorView("error");    // No default
////        r.setExceptionAttribute("ex");     // Default is "exception"
//        r.setExcludedExceptions(AccessDeniedException.class);
////        r.setWarnLogCategory("example.MvcLogger");     // No default
//        r.setExceptionMappings(mappings);  // None by default
//
//        return r;
//    }


    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    private final String KEY = "e9862d10db8e7de7877e9d28ef8153b4f0e209b8";

    @Bean
    public RememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices services = new TokenBasedRememberMeServices(KEY, userDetailsService);
        services.setAlwaysRemember(true);
        return services;
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}