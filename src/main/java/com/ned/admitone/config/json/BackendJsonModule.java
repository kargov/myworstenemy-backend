package com.ned.admitone.config.json;


import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ned.admitone.domain.admin.login.LoginMixIns;
import com.ned.admitone.domain.admin.login.response.LoginResponse;
import com.ned.admitone.domain.user.User;
import com.ned.admitone.domain.user.json.UserMixIns;

class BackendJsonModule extends SimpleModule {

    private static final long serialVersionUID = 1L;
    private static final Version VERSION = VersionUtil.parseVersion("1.0-SNAPSHOT", "com.ned.admitone", "admitone");

    BackendJsonModule() {
        super(VERSION);
        setMixInAnnotation(LoginResponse.class, LoginMixIns.LoginResponseMixIn.class);
        setMixInAnnotation(User.class, UserMixIns.UserMixIn.class);
    }
}
