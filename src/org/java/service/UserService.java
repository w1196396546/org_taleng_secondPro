package org.java.service;

import org.java.entity.UserInfo;

public interface UserService {

    //根据用户名查询是否已存在相同的用户名
    int getCount(String userEmail);
    //用户名密码是否正确
    int getLoginCheck(String email,String pwd);
    //连接上一个方法，如果正确，取出用户信息
    UserInfo getUser(String email, String pwd);
    //修改密码的方法
    void updatePwd(String email,String pwd);
    //用户登录成功，取到购物车中的总数量用显示在页面
    int getUserShoppingCartCount(String email);
}
