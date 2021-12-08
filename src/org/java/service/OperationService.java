package org.java.service;

import org.apache.ibatis.annotations.Param;
import org.java.entity.GoodsInfo;
import org.java.entity.IpShoppingCart;
import org.java.entity.UserCart;

import java.util.List;

public interface OperationService {

    int getIpShoppingCartCount(String ip);

    List<IpShoppingCart> getIpShoppingCartByIp(String ip);

    //得到ip购物车中所有的商品信息
    List<UserCart> getAllIpShoppingCartContent(String goodsId, String ip);

}
