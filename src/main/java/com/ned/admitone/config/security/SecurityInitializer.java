package com.ned.admitone.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityInitializer() {
        super(SecurityConfig.class);
    }
}
