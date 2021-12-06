package org.java.service.impl;

import org.java.dao.RegisterMapper;
import org.java.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("registerService")
public class RegisterSerivceImpl implements RegisterService {
    @Autowired
    private RegisterMapper registerMapper;
    @Override
    public void addUser(String userEmail, String userPwd) {
        registerMapper.addUser(userEmail,userPwd);
    }
}
