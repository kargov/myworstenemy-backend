package com.ned.admitone.domain.user.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ned.admitone.domain.user.UserInterface;

public class UserMixIns {

    private UserMixIns() {
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    public abstract static class UserMixIn implements UserInterface {
        @Override
        @JsonProperty
        public abstract Long getId();

        @Override
        @JsonProperty
        public abstract String getUsername();
    }
}
