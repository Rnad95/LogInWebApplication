//package com.example.loginapplication.util;
//
//import com.example.saml.configuration.Twilioproperties;
//import com.twilio.Twilio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TwilioInitiazer {
//
//	@Autowired
//	public TwilioInitiazer(Twilioproperties twilioproperties)
//	{
//		Twilio.init(twilioproperties.getAccountSid(), twilioproperties.getAuthToken());
//		System.out.println("Twilio initialized with account-"+twilioproperties.getAccountSid());
//	}
//}
