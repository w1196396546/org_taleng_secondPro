package org.java.dao;

import org.apache.ibatis.annotations.Param;
import org.java.entity.*;

import java.util.List;

public interface OperationMapper {

    List<GoodsInfo> getGoodsInfo();

    //用户没有登录，查询ip购物车是否存在数据
    int getIpShoppingCartCount(@Param("ip") String ip);
    //用户没有登录，IP购物车存在数据，进行取值
    List<IpShoppingCart> getIpShoppingCartByIp(@Param("ip") String ip);

    //根据商品id，查询出所有的ip购物车中的数据
    UserCart getAllIpShoppingCartContent(@Param("goodsId") String goodsId, @Param("ip")String ip);

    //IP购物车的购物车数量减少
    void updateLessIpShoppingCartGoodsNum(@Param("goodsId") String GoodsId,@Param("ip")String ip);

    //IP购物车的购物车数量增加
    void updateAddIpShoppingCartGoodsNum(@Param("goodsId") String goodsId,@Param("ip")String ip);

    //初始化加载省份信息
    List<Province> getProvince();
    //得到城市信息
    List<City> getCity(@Param("pid")Integer pid);
    //得到区域信息
    List<Areas> getArea(@Param("cid")Integer cid);

}
