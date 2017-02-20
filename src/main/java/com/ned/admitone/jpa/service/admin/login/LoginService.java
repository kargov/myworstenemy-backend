package com.ned.admitone.jpa.service.admin.login;

import com.ned.admitone.domain.admin.login.response.LoginResponse;
import com.ned.admitone.domain.admin.login.response.LoginResponseInterface;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.service.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceInterface {

    private final UserServiceInterface service;

    @Autowired
    public LoginService(UserServiceInterface service) {
        this.service = service;
    }

    @Override
    public LoginResponseInterface login(String username, String password) {
        LoginResponse response = new LoginResponse();
        User user = service.getUser(username);
        if(userExists(user)) {
            StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
            if(passwordEncoder.matches(password,user.getPassword())) {
                response.setSuccess(true);
                response.setUsername(user.getUsername());
            }
        }
        return response;
    }

    private boolean userExists(User user) {
        return null != user;
    }
}
