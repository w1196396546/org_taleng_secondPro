package org.java.service.impl;

import org.java.dao.UserMapper;
import org.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public int getCount(String userEmail) {
        int count = userMapper.getCount(userEmail);
        return count;
    }
}
