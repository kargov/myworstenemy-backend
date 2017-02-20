package com.ned.admitone.service.security;

import com.ned.admitone.domain.user.Role;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.repository.user.UserRepository;
import com.ned.admitone.jpa.service.security.AuthenticationService;
import com.ned.admitone.utills.TestObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationServiceTest {

    @Test
    public void testLoadUserByUsername() {
        //Given
        User user = TestObjectFactory.getUser(1L,"Ned","secret", Role.ROLE_CUSTOMER);

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        //When
        AuthenticationService service = new AuthenticationService(userRepository);
        UserDetails userDetails = service.loadUserByUsername(user.getUsername());

        //Then
        Assert.assertNotNull(userDetails);
        Assert.assertEquals(user.getUsername(),userDetails.getUsername());
        Assert.assertEquals(user.getPassword(),userDetails.getPassword());
        Assert.assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_CUSTOMER.name())));
        Mockito.verify(userRepository).findByUsername(user.getUsername());
    }
}
