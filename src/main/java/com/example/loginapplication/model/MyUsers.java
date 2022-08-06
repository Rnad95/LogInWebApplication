package com.example.loginapplication.model;

import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Date;

@Entity
public class MyUsers implements UserDetails {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id ;
    @NonNull
    private String validCode;
    @NonNull
    private Date validDate = new Date();

    @NonNull
    private  String password;
    @NonNull
    private  String username;
    private static final long CODE_FIVE_MINUTE_VALIDATION = 5 * 60 * 1000;
    public MyUsers() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MyUsers(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
//        if(isRequired())
//            return validCode;
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidCode() {
        return validCode;
    }
    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
    public Date getValidDate() {
        return validDate;
    }
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public boolean isRequired(){
        long otpRequestTimeInMilliSecond = this.validDate.getTime();
        System.out.println("REQUIRED " + otpRequestTimeInMilliSecond + CODE_FIVE_MINUTE_VALIDATION );
        return otpRequestTimeInMilliSecond + CODE_FIVE_MINUTE_VALIDATION < System.currentTimeMillis();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
