package com.example.loginapplication.controller;


import com.example.loginapplication.Service.UserService;
import com.example.loginapplication.model.MyUsers;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class LoginController {

    private Authentication auth;
    @Autowired
    UserService userService;
    @GetMapping("/sign-up")
    String signUp(){
        return "sign-up";
    }
    @PostMapping("/sign-up")
    String registeration(@ModelAttribute MyUsers myUser){
        if(userService.findByUsername(myUser.getUsername())!=null) return "sign-up";
        myUser.setPassword(myUser.getPassword());
        myUser.setUsername(myUser.getUsername());
        userService.save(myUser);
        return "login";

    }
    @GetMapping("/login")
    String loginPage() {
        return "login";
    }
    @GetMapping("/")
    String home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth instanceof AnonymousAuthenticationToken? "home":"login-email");
    }
    @GetMapping("/home")
    String LoginGeneratedCode() {
        return "home";
    }
    @PostMapping("/verifycode")
    String verifyCode(@RequestParam("code") String code, @RequestParam("email") String email) {
        MyUsers myUsers = userService.findByUsername(email);
        if (myUsers.getValidCode().equals(code) && myUsers.isValid()) {
            myUsers.setValidCode(null);
            return "home";
        }
        return "redirect:/home";
    }
    @GetMapping("/saml/sso")
    String SAMLLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication.isAuthenticated()) ?"home":"login";
    }
}