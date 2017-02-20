package com.ned.admitone.jpa.service.user;

import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
