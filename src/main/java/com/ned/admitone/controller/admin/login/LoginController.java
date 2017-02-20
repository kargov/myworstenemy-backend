package com.ned.admitone.controller.admin.login;

import com.ned.admitone.domain.admin.login.response.LoginResponseInterface;
import com.ned.admitone.jpa.service.admin.login.LoginServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class LoginController {

    private final LoginServiceInterface service;

    @Autowired
    public LoginController(LoginServiceInterface service) {
        this.service = service;
    }

    @CrossOrigin
    @RequestMapping(value="api/admin/login/", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public LoginResponseInterface cancellation(@RequestParam("username") String username, @RequestParam("password") String password) {
        log.debug("Request to LoginController");
        return service.login(username,password);
    }
}