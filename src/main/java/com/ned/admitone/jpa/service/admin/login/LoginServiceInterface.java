package com.ned.admitone.jpa.service.admin.login;

import com.ned.admitone.domain.admin.login.response.LoginResponseInterface;

public interface LoginServiceInterface {
    LoginResponseInterface login(String username, String password);

}
