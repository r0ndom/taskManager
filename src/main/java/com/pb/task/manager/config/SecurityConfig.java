package com.pb.task.manager.config;

import com.pb.task.manager.service.security.SimpleAuthenticationSuccessHandler;
import com.pb.task.manager.service.security.UserAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by Mednikov on 26.02.2016.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new UserAuthenticationProvider();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/app/tasks/**").
                access("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER', 'ROLE_TESTER', 'ROLE_MANAGER')");
        http.authorizeRequests().antMatchers("/app/users/**").access("hasRole('ROLE_CONTROL')");
        http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").
                defaultSuccessUrl("/app/tasks/").failureUrl("/login?error").successHandler(authenticationSuccessHandler());
        http.logout().logoutSuccessUrl("/login?logout");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}