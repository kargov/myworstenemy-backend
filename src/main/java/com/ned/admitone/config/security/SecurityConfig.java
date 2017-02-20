package com.ned.admitone.config.security;

import com.ned.admitone.jpa.service.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.ned.admitone"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService service;

    @Autowired
    public SecurityConfig(AuthenticationService service) {
        this.service = service;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
            .and()
                .authorizeRequests()
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .antMatchers("/api/customers/**").hasRole("CUSTOMER")
                        .antMatchers("/api/admin/**").hasRole("ADMIN")
            .and()
                .httpBasic()
            .and()
                .csrf()
                    .disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new StandardPasswordEncoder());
    }
}
