package com.ned.admitone.domain.admin.login.response;


import lombok.Getter;
import lombok.Setter;

public class LoginResponse implements LoginResponseInterface {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private boolean success;
}
