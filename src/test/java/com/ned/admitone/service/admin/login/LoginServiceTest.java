package com.ned.admitone.service.admin.login;

import com.ned.admitone.domain.admin.login.response.LoginResponseInterface;
import com.ned.admitone.domain.user.Role;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.jpa.service.admin.login.LoginService;
import com.ned.admitone.jpa.service.user.UserServiceInterface;
import com.ned.admitone.utills.TestObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class LoginServiceTest {

    @Test
    public void testLogin() {
        //Given
        User user = TestObjectFactory.getUser(1L,"ram","53171a42be158338548e209a9398173b87524258bcbd09bc1b5fa23f0ff8c7a1615ce2936a6c5bc0", Role.ROLE_CUSTOMER);
        UserServiceInterface userService = Mockito.mock(UserServiceInterface.class);
        Mockito.when(userService.getUser(user.getUsername())).thenReturn(user);
        //When
        LoginService service = new LoginService(userService);
        LoginResponseInterface response = service.login("ram","ram");
        //Then
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals(user.getUsername(),response.getUsername());
    }

    @Test
    public void testLoginFails() {
        //Given
        User user = TestObjectFactory.getUser(1L,"ram","53171a42be158338548e209a9398173b87524258bcbd09bc1b5fa23f0ff8c7a1615ce2936a6c5bc0", Role.ROLE_CUSTOMER);
        UserServiceInterface userService = Mockito.mock(UserServiceInterface.class);
        Mockito.when(userService.getUser(user.getUsername())).thenReturn(user);

        //When
        LoginService service = new LoginService(userService);
        LoginResponseInterface response = service.login("ram","I don't know the password so I type random stuff....");
        //Then
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isSuccess());
        Assert.assertNull(response.getUsername());
    }
}
