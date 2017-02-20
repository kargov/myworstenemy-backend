package com.ned.admitone.domain.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=User.class)
public interface UserInterface {
    Long getId();
    String getUsername();
    Role getRole();
}
