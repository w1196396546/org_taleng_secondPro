package org.java.service.impl;

import org.java.dao.UserMapper;
import org.java.entity.*;
import org.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<IpShoppingCart> getIpShoppingCartByIp(String ip) {
        List<IpShoppingCart> list = userMapper.getIpShoppingCartByIp(ip);
        return list;
    }

    @Override
    public List<UserShoppingCart> getUserShoppingCartByUserId(String userId) {
        List<UserShoppingCart> userList = userMapper.getUserShoppingCartByUserId(userId);
        return userList;
    }

    @Override
    public void updateUserShoppingCartGoodsNumByUserEmail(String email,Integer num) {
        System.out.println(email);
        userMapper.updateUserShoppingCartGoodsNumByUserEmail(email,num);
    }

    @Override
    public void addUserShoppingCart(String email, String goodsId, Integer goodsNum) {
        userMapper.addUserShoppingCart(email,goodsId,goodsNum);
    }

    @Override
    public void delIpShoppingCartByIp(String ip) {
        userMapper.delIpShoppingCartByIp(ip);
    }

    @Override
    public UserCart getAllUserShoppingCartContent(String goodId,String email) {
        UserCart list = userMapper.getAllUserShoppingCartContent(goodId,email);
        return list;
    }

    @Override
    public void updateLessUserGoodsCart(String email, String goodId) {
        userMapper.updateLessUserGoodsCart(email,goodId);
    }

    @Override
    public void updateAddUserGoodsCart(String email, String goodId) {
        userMapper.updateAddUserGoodsCart(email,goodId);
    }
}
