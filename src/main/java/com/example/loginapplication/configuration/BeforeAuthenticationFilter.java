package com.example.loginapplication.configuration;

import com.example.loginapplication.Service.UserService;
import com.example.loginapplication.model.MyUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Component
public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserService userService;

    BeforeAuthenticationFilter(){
        super.setUsernameParameter("username");
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login-process","POST"));

    }
    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        System.out.println("setAuthenticationManager");
        super.setAuthenticationManager(authenticationManager);
    }

    @Autowired
    @Override
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        System.out.println("setAuthenticationSuccessHandler" + getUsernameParameter());
        super.setAuthenticationSuccessHandler(successHandler);
    }

    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        System.out.println("setAuthenticationFailureHandler" + getUsernameParameter());

        super.setAuthenticationFailureHandler(failureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("attemptAuthentication");
        MyUsers myUser = userService.getUserByEmail(request.getParameter("username"));
        System.out.println("MY USER ==> "+ myUser);
        try {
            userService.generateOTPCode(myUser);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new AuthenticationServiceException("Error While Sending Code to Your Email");
        }

        return super.attemptAuthentication(request, response);
    }


}
