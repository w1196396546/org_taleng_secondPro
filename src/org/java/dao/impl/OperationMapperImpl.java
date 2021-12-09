package org.java.dao.impl;

import org.java.dao.OperationMapper;
import org.java.entity.GoodsInfo;
import org.java.entity.IpShoppingCart;
import org.java.entity.UserCart;
import org.java.util.JdbcUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OperationMapperImpl implements OperationMapper {

    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JdbcUtil.getDs());

    @Override
    public List<GoodsInfo> getGoodsInfo() {
        String sql="select * from goods";
        List<GoodsInfo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GoodsInfo.class));
        return list;
    }

    @Override
    public int getIpShoppingCartCount(String ip) {
        return 0;
    }

    @Override
    public List<IpShoppingCart> getIpShoppingCartByIp(String ip) {
        return null;
    }

    @Override
    public UserCart getAllIpShoppingCartContent(String goodsId, String ip) {
        return null;
    }

    @Override
    public void updateLessIpShoppingCartGoodsNum(String GoodsId, String ip) {

    }

    @Override
    public void updateAddIpShoppingCartGoodsNum(String goodsId, String ip) {

    }
}
