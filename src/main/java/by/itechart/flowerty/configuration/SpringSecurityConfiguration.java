package by.itechart.flowerty.configuration;

import by.itechart.flowerty.dao.repository.RoleRepository;
import by.itechart.flowerty.model.Right;
import by.itechart.flowerty.model.Role;
import by.itechart.flowerty.security.*;
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

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Rostislav on 26-Mar-15
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
    private RoleRepository roleRepository;

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
        List<Role> roles = roleRepository.findAll();
        for (int i = 0; i < roles.size(); ++i) {
            Set<Right> rights = roles.get(i).getRights();
            for (Iterator<Right> it = rights.iterator(); it.hasNext(); ) {
                Right right = it.next();
                http
                        .authorizeRequests()
                        .antMatchers("/" + right.getName() + "/**")
                        .access("hasRole('ROLE_" + roles.get(i).getName() + "')")
                ;
            }
        }

//        http
////                .addFilterBefore(new CustomUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/user/**")
//                .access("hasRole('ROLE_ADMIN')")
//                ;
//
//        http
//                .authorizeRequests()
//                .antMatchers("/contact/**")
//                .access("hasRole('ROLE_SUPERVISOR')")
//                ;

        http
                .rememberMe()
                .rememberMeServices(rememberMeServices())
                .key(KEY)
            .and()
                .formLogin()
                .loginPage("/#/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authSuccess)
                .failureHandler(authFailure)
            .and()
                .logout()
            .and()
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .csrf().csrfTokenRepository(csrfTokenRepository())
//            .and()
//                .addFilter(customUsernamePasswordAuthenticationFilter())

        ;
    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter()
            throws Exception {
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
        customUsernamePasswordAuthenticationFilter
                .setAuthenticationManager(authenticationManagerBean());
//        customUsernamePasswordAuthenticationFilter
//                .setAuthenticationSuccessHandler(customSuccessHandler());
        return customUsernamePasswordAuthenticationFilter;
    }

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

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}