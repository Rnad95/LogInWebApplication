//package com.example.loginapplication.configuration;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Order (value = 1)
//@EnableWebSecurity
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//
//    private String[] PUBLIC_ENDPOINTS = {
//            "/",
//            "/login-email",
//            "/sign-up",
//            "/sendotp",
//            "/login" ,
//            "/css/**" ,
//            "/js/**" ,
//            "/assets/**"
//    };
//
////    @Autowired
////    @Lazy
////    BeforeAuthenticationFilter beforeAuthenticationFilter;
//
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().and().csrf().disable()
//                .authorizeRequests().antMatchers(PUBLIC_ENDPOINTS).permitAll()
//                .anyRequest().authenticated()
//                .and()
////                .addFilterBefore(beforeAuthenticationFilter,BeforeAuthenticationFilter.class)
//                .formLogin()
//                .usernameParameter("username")
//                .loginPage("/login")
//                .loginProcessingUrl("/login-process")
//                .defaultSuccessUrl("/login-code")
//                .failureUrl("/login")
//                .and()
//                .logout()
//                .logoutUrl("/logout-process")
//                .logoutSuccessUrl("/");
//    }
//
//
//}