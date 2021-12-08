package org.java.dao;

import org.apache.ibatis.annotations.Param;
import org.java.entity.GoodsInfo;
import org.java.entity.IpShoppingCart;
import org.java.entity.UserCart;

import java.util.List;

public interface OperationMapper {

    List<GoodsInfo> getGoodsInfo();

    //用户没有登录，查询ip购物车是否存在数据
    int getIpShoppingCartCount(@Param("ip") String ip);
    //用户没有登录，IP购物车存在数据，进行取值
    List<IpShoppingCart> getIpShoppingCartByIp(@Param("ip") String ip);

    //根据商品id，查询出所有的ip购物车中的数据
    List<UserCart> getAllIpShoppingCartContent(@Param("goodsId") String goodsId, @Param("ip")String ip);

}
