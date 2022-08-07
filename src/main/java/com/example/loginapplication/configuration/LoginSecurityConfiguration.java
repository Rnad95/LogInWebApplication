package com.example.loginapplication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@ConditionalOnProperty(
        value="lambda.application",
        havingValue = "true",matchIfMissing= true)

@EnableWebSecurity
public class LoginSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private String[] PUBLIC_ENDPOINTS = {
            "/",
            "/sign-up",
            "/login" ,
            "/sendotp",
            "/saml**",
            "verifytop",
            "/login-code",
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