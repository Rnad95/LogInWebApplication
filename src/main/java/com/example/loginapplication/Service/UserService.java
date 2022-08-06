package com.example.loginapplication.Service;

import com.example.loginapplication.model.MyUsers;
import com.example.loginapplication.repository.UsersRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JavaMailSender mailSender;


    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public MyUsers save(MyUsers user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public MyUsers getUserById(Long Id){
        return usersRepository.getById(Id);
    }

    public MyUsers getUserByEmail(String email){
        return usersRepository.findByUsername(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUsers user = usersRepository.findByUsername(email);
        if(user == null) throw new UsernameNotFoundException("User Not Found");
        return user;
    }

    public List<MyUsers> findAll() {
        return usersRepository.findAll();
    }

    public MyUsers findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public void generateOTPCode(MyUsers myUsers) throws MessagingException, UnsupportedEncodingException {
        MyUsers myUsers1 = usersRepository.findByUsername(myUsers.getUsername());
        String code = RandomString.make(6);
//        String encodeOTP = passwordEncoder().encode(code);
        myUsers1.setValidCode(code);
        myUsers1.setValidDate(new Date());
//        usersRepository.save(myUsers1);
        System.out.println("******************************************");
        System.out.println("USER FROM BEFORE CLASS "+ myUsers1);
//        sendOTPCodeByEmail(myUsers, code);

    }

    private void sendOTPCodeByEmail(MyUsers myUsers, String code) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("r.khawatreh13@gmail.com", "Login Support");
        helper.setTo(myUsers.getUsername());

        String subject = "Verify Code - Team Support";

        String content = "<p><b>Hello</b>, " + myUsers.getUsername() + "</p>"
                + "<p>For security reason, you have to the code below "
                + "One Time Password to login:</p>"
                + "<p><b>" + code + "</b></p>"
                + "<hr/>"
                + "<b>Best Regards,</b><br/>"
                + "Team Support";
        helper.setSubject(subject);

        helper.setText(content, true);
        System.out.println("SUCCESS BEFORE SENDING => "+message.getFrom());

        mailSender.send(message);
        System.out.println("SUCCESS AFTER SENDING => "+message.getFrom());

    }

    public String encodePassword(String code) {
        return passwordEncoder().encode(code);
    }
}
