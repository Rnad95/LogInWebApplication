package com.example.loginapplication.handler;

//import com.example.loginapplication.Service.PhoneVerificationService;
import com.example.loginapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    @Autowired
//    PhoneVerificationService phoneSMSService;

    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        System.out.println("onAuthenticationSuccess");
//        MyUsers myUser = userService.getUserByEmail(request.getParameter("username"));
//        System.out.println("MY USER ==> "+ myUser);
//        if (myUser==null) {
//            if (myUser.isRequired())
//                super.onAuthenticationSuccess(request, response, chain, authentication);
//            try {
//
//                userService.generateOTPCode(myUser);
//
//                throw new InsufficientAuthenticationException("OTP");
//            } catch (MessagingException | UnsupportedEncodingException e) {
//                throw new AuthenticationServiceException("Error While Sending Code to Your Email");
//            }
//        }else{
//            System.out.println("USER NULL");
//        }
//        super.setDefaultTargetUrl("/login-code");

        super.onAuthenticationSuccess(request, response, chain, authentication);

    }
}
