//package com.example.loginapplication.controller;
//
//
////import com.example.saml.Service.PhoneVerificationService;
//import com.example.loginapplication.Service.UserService;
//import com.example.loginapplication.Service.VerificationResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.view.RedirectView;
//
//@Controller
//public class TwilioController {
//
////    @Autowired
////    PhoneVerificationService phoneSMSService;
//
//    @Autowired
//    UserService userService;
//
//    @PostMapping("/sendotp")
//    public ResponseEntity<String> sendotp(@RequestParam("phone") String phone) {
//        VerificationResult result = phoneSMSService.startVerification(phone);
//        if (result.isValid()) return new ResponseEntity<>("Otp Sent..", HttpStatus.OK);
//        return new ResponseEntity<>("Otp failed to sent..", HttpStatus.BAD_REQUEST);
//    }
//
//    @PostMapping("/verifyotp")
//    RedirectView checkOTP(@RequestParam("phone") String phone, @RequestParam("otp") String otp) {
//
//        VerificationResult result = phoneSMSService.checkVerification(phone, otp);
//        System.out.println("RESULT => "+ result);
////        if (result.isValid()) return new RedirectView("/");
//        return new RedirectView("/login");
//    }
//
//
//
//}
