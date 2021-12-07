package org.java.dao;

import org.apache.ibatis.annotations.Param;
import org.java.entity.UserInfo;

public interface UserMapper {

    int getCount(@Param("email") String userEmail);

    int getLoginCheck(@Param("email")String email,@Param("pwd")String pwd);
    UserInfo getUser(@Param("email")String email, @Param("pwd")String pwd);
    void updatePwd(@Param("email")String email,@Param("pwd")String pwd);
    //用户登录成功之后,先根据ip地址判断这个ip购物车是否存在数据
    int getIpShoppingCartCount(@Param("ip")String ip);
    //用户登录成功之后，加载购物车物品的总数
    int getUserShoppingCartCount(@Param("email")String email);
}
