package com.example.loginapplication.configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import static org.springframework.security.extensions.saml2.config.SAMLConfigurer.saml;

@Order (value =3)
@EnableWebSecurity
@Configuration
public class SAMLSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${security.saml2.metadata-url}")
    String metadataUrl;

    @Value("${server.ssl.key-alias}")
    String keyAlias;

    @Value("${server.ssl.key-store-password}")
    String password;

    @Value("${server.port}")
    String port;

    @Value("${server.ssl.key-store}")
    String keyStoreFilePath;

    private String[] PUBLIC_ENDPOINTS = {
            "/",
            "/sign-up",
            "/login" ,
            "/sendotp",
            "/saml**",
            "/login-email" ,
            "/css/**" ,
            "/js/**" ,
            "/assets/**"
    };


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests().antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/logout-process")
                .logoutSuccessUrl("/login")
                .and()
                .apply(saml())
                .serviceProvider()
                .keyStore()
                .storeFilePath(this.keyStoreFilePath)
                .password(this.password)
                .keyname(this.keyAlias)
                .keyPassword(this.password)
                .and()
                .protocol("https")
                .hostname(String.format("%s:%s", "localhost", this.port))
                .basePath("/")
                .and()
                .identityProvider()
                .metadataFilePath(this.metadataUrl);
    }
}