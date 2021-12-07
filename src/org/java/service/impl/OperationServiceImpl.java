package org.java.service.impl;

import org.java.dao.OperationMapper;
import org.java.entity.GoodsInfo;
import org.java.entity.IpShoppingCart;
import org.java.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("operationService")
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationMapper operationMapper;
    @Override
    public int getIpShoppingCartCount(String ip) {
        int count = operationMapper.getIpShoppingCartCount(ip);
        return count;
    }

    @Override
    public List<IpShoppingCart> getIpShoppingCartByIp(String ip) {
        List<IpShoppingCart> list = operationMapper.getIpShoppingCartByIp(ip);
        return list;
    }

    @Override
    public List<GoodsInfo> getAllIpShoppingCartContent(String goodsId) {
        List<GoodsInfo> ipGoodsList = operationMapper.getAllIpShoppingCartContent(goodsId);
        return ipGoodsList;
    }
}
