package org.java.service;

import org.apache.ibatis.annotations.Param;
import org.java.entity.*;

import java.util.List;

public interface OperationService {

    int getIpShoppingCartCount(String ip);

    List<IpShoppingCart> getIpShoppingCartByIp(String ip);

    //得到ip购物车中所有的商品信息
    UserCart getAllIpShoppingCartContent(String goodsId, String ip);

    //IP购物车商品数量减1
    void updateLessIpShoppingCartGoodsNum(String goodsId,String ip);

    //IP购物车商品数量加1
    void updateAddIpShoppingCartGoodsNum(String goodsId,String ip);

    List<Province> getProvince();

    List<City> getCity(Integer pid);

    List<Areas> getArea(Integer cid);
}
