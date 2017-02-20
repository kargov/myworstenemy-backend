package com.ned.admitone.jpa.service.user;

import com.ned.admitone.domain.user.User;

public interface UserServiceInterface {
    User getUser(String username);
}
