//package com.example.loginapplication.Service;
//
//import com.twilio.exception.ApiException;
//import com.twilio.rest.verify.v2.service.Verification;
//import com.twilio.rest.verify.v2.service.VerificationCheck;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PhoneVerificationService {
//
//	private final Twilioproperties TwilioProperties;
//
//	@Autowired
//	public PhoneVerificationService(Twilioproperties twilioproperties) {
//		this.TwilioProperties =twilioproperties;
//	}
//
//
//    public VerificationResult startVerification(String phone) {
//        try {
//            Verification verification = Verification.creator(TwilioProperties.getServiceId(), phone, "sms").create();
//            if("approved".equals(verification.getStatus())|| "pending".equals(verification.getStatus())) {
//			return new VerificationResult(verification.getSid());
//			}
//        } catch (ApiException exception) {
//            return new VerificationResult(new String[] {exception.getMessage()});
//        }
//        return null;
//    }
//
//    public VerificationResult checkVerification(String phone, String code) {
//        try {
//            VerificationCheck verification = VerificationCheck.creator(TwilioProperties.getServiceId(), code).setTo(phone).create();
//            if("approved".equals(verification.getStatus())) {
//                return new VerificationResult(verification.getSid());
//            }
//            return new VerificationResult(new String[]{"Invalid code."});
//        } catch (ApiException exception) {
//            return new VerificationResult(new String[]{exception.getMessage()});
//        }
//    }
//
//}
