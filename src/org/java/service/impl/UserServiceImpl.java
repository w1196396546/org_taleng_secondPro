package org.java.service.impl;

import org.java.dao.UserMapper;
import org.java.entity.UserInfo;
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

    @Override
    public int getLoginCheck(String email, String pwd) {
        int count = userMapper.getLoginCheck(email, pwd);
        return count;
    }

    @Override
    public UserInfo getUser(String email, String pwd) {
        UserInfo user = userMapper.getUser(email, pwd);
        return user;
    }

    @Override
    public void updatePwd(String email, String pwd) {
        System.out.println(email+" "+pwd);
        userMapper.updatePwd(email,pwd);
        System.out.println("修改成功");
    }

    @Override
    public int getIpShoppingCartCount(String ip) {
        int count = userMapper.getIpShoppingCartCount(ip);
        return count;
    }

    @Override
    public int getUserShoppingCartCount(String email) {
        int count = userMapper.getUserShoppingCartCount(email);
        return count;
    }
}
