package com.nipu.init;

import com.nipu.service.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@ComponentScan("com.nipu")
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService customUserDetailsService;

    @Bean
    public CustomSuccessHandler getSuccessHandler(){
        return new CustomSuccessHandler();
    }

    @Override
    protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.userDetailsService(customUserDetailsService)
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers(Constants.E403_PAGE).permitAll()
                .antMatchers(Constants.VERIFICATION_PAGES_ROOT+"/*").hasAuthority(Constants.VERIFICATOR_ROLE)
                .antMatchers(Constants.ADMIN_PAGES_ROOT+"/*").hasAuthority(Constants.ADMIN_ROLE)
                .antMatchers(Constants.ACHIEV_PAGES_ROOT+"/*").hasAuthority(Constants.APPLICANT_ROLE)
                .and()
                .formLogin()
                .defaultSuccessUrl(Constants.INDEX_PAGE)
                .loginPage(Constants.LOGIN_PAGE)
                .permitAll()
                .successHandler(getSuccessHandler())
                .and()
                .logout()
                .logoutSuccessUrl(Constants.INDEX_PAGE)
                .logoutUrl(Constants.LOGOUT_PAGE)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
        http
                .exceptionHandling().accessDeniedPage(Constants.E403_PAGE);
        http.csrf().disable();
    }

}
