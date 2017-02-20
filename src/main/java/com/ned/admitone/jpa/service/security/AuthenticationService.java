package com.ned.admitone.jpa.service.security;

import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        User user = userRepository.findByUsername(s);
        if(null != user) {
            userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                                             user.getPassword(),
                                            true,
                                            true,
                                            true,
                                            true,
                                             AuthorityUtils.createAuthorityList(user.getRole().name()));
        }
        return userDetails;
    }
}
