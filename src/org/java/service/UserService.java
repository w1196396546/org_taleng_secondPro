package org.java.service;

import org.apache.ibatis.annotations.Param;
import org.java.entity.*;

import java.util.List;

public interface UserService {

    //根据用户名查询是否已存在相同的用户名
    int getCount(String userEmail);
    //用户名密码是否正确
    int getLoginCheck(String email,String pwd);
    //连接上一个方法，如果正确，取出用户信息
    UserInfo getUser(String email, String pwd);
    //修改密码的方法
    void updatePwd(String email,String pwd);
    //用户登录成功，先根据ip地址查询是否存在数据
    int getIpShoppingCartCount(String ip);
    //存在数据在把数据找出来
    List<IpShoppingCart> getIpShoppingCartByIp(String ip);
    //得到所有的用户购物车信息
    List<UserShoppingCart> getUserShoppingCartByUserId(String userId);
    //用户登录成功，取到购物车中的总数量用显示在页面
    int getUserShoppingCartCount(String email);
    //如果商品存在，在商品数量的基础上加1
    void updateUserShoppingCartGoodsNumByUserEmail(String email,Integer num);
    //如果商品不存，直接添加
    void addUserShoppingCart(String email,String goodsId,Integer goodsNum);
    //全部执行完成，删除ip购物车表的数据
    void delIpShoppingCartByIp(String ip);
    //点击购物车时，找出所有的购物信息
    UserCart getAllUserShoppingCartContent(String goodId, String email);
    //用户购物车数量减少的操作
    void updateLessUserGoodsCart(String email,String goodId);
    //用户购物车数量减少的操作
    void updateAddUserGoodsCart(String email,String goodId);

}
