package org.java.dao;

import org.apache.ibatis.annotations.Param;
import org.java.entity.IpShoppingCart;
import org.java.entity.UserInfo;
import org.java.entity.UserShoppingCart;

import java.util.List;

public interface UserMapper {

    int getCount(@Param("email") String userEmail);

    int getLoginCheck(@Param("email")String email,@Param("pwd")String pwd);
    UserInfo getUser(@Param("email")String email, @Param("pwd")String pwd);
    void updatePwd(@Param("email")String email,@Param("pwd")String pwd);
    //用户登录成功之后,先根据ip地址判断这个ip购物车是否存在数据
    int getIpShoppingCartCount(@Param("ip")String ip);
    //如果存在数据执行的方法,找出指定IP地址的所有的信息
    List<IpShoppingCart> getIpShoppingCartByIp(@Param("ip") String ip);
    //得到所有的用户购物车信息
    List<UserShoppingCart> getUserShoppingCartByUserId(@Param("userId")String userId);
    //如果商品id存在，那么在原来的基础上加上ip购物车的商品数量
    void updateUserShoppingCartGoodsNumByUserEmail(@Param("userEmail")String email,@Param("num")Integer num);
    //如果商品不存在，那么直接根据用户信息存到购物车表中
    void addUserShoppingCart(@Param("email")String email,@Param("goodsId")String goodId,@Param("goodsNum")Integer goodsNum);
    //执行完成，删除IP购物车中对应的数据
    void delIpShoppingCartByIp(@Param("ip")String ip);
    //用户登录成功之后，加载购物车物品的总数
    int getUserShoppingCartCount(@Param("email")String email);
}
