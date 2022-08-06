package com.example.loginapplication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order (value = 2)
@EnableWebSecurity
@Configuration
public class LoginSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private String[] PUBLIC_ENDPOINTS = {
            "/", // dont change it
            "/sign-up",
            "/login" ,
            "/sendotp",
            "verifytop",
            "/login-email" ,
            "/css/**" ,
            "/js/**" ,
            "/assets/**"
    };

    @Autowired
    @Lazy
    BeforeAuthenticationFilter beforeAuthenticationFilter;

    @Bean (BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests().antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(beforeAuthenticationFilter,BeforeAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .loginProcessingUrl("/login-process")
                .defaultSuccessUrl("/login-code")
                .and()
                .logout()
                .logoutUrl("/logout-process")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID");


    }
}