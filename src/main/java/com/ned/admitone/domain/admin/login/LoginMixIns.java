package com.ned.admitone.domain.admin.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ned.admitone.domain.admin.login.response.LoginResponseInterface;

public class LoginMixIns {
    private LoginMixIns() {
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public abstract static class LoginResponseMixIn implements LoginResponseInterface {
        @Override
        @JsonProperty
        public abstract String getUsername();

        @Override
        @JsonProperty
        public abstract boolean isSuccess();
    }
}
