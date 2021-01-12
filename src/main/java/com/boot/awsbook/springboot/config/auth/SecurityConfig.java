package com.boot.awsbook.springboot.config.auth;

import com.boot.awsbook.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Enable Spring Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()  // for using h2 console
                .and()
                    .authorizeRequests()  // authorizing by url and antMatchers require authorizeRequest()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())  // url or http, only user can use /api/v1/**
                    .anyRequest().authenticated()  // login user can use anyother url
                .and()
                    .logout().logoutSuccessUrl("/")
                .and()
                    .oauth2Login() // start OAuth2 login function
                    .userInfoEndpoint() // after login load user info
                    .userService(customOAuth2UserService); // after login function in service implement userService
    }
}
