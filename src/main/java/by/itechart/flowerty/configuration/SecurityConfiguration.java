package by.itechart.flowerty.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015 
 *
 */
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
	        .authorizeRequests()
	            .antMatchers("/user/list/").permitAll()
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/signin")
	            .permitAll()
	            .failureUrl("/error")
	            .loginProcessingUrl("/authenticate")
	            .and()
	        .logout()
	            .logoutUrl("/logout")
	            .permitAll()
	            .logoutSuccessUrl("/logout");//TODO:need implement logout
	}
}
