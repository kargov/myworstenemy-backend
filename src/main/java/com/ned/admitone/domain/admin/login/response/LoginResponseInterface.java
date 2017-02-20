package com.ned.admitone.domain.admin.login.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=LoginResponse.class)
public interface LoginResponseInterface {
    String getUsername();
    boolean isSuccess();
}
