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
    private boolean enableCode;
    private static final long CODE_FIVE_MINUTE_VALIDATION = 5 * 60 * 1000;

    public boolean isEnableCode() {
        return enableCode;
    }

    public void setEnableCode(boolean enableCode) {
        this.enableCode = enableCode;
    }

    @NonNull
    private  String password;
    @NonNull
    private  String username;
    public MyUsers() {
    }
    public MyUsers(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    public boolean isRequired(){
        long otpRequestTimeInMilliSecond = this.validDate.getTime();
        System.out.println("REQUIRED " + otpRequestTimeInMilliSecond + CODE_FIVE_MINUTE_VALIDATION );
        return otpRequestTimeInMilliSecond + CODE_FIVE_MINUTE_VALIDATION < System.currentTimeMillis();
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
    public String getValidCode() {
        return validCode;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }
    public Date getValidDate() {
        return validDate;
    }
    public boolean isValid() {
        return this.validDate.getTime() + CODE_FIVE_MINUTE_VALIDATION > System.currentTimeMillis();
    }

    public void clearCode() {
        this.validCode =null;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
