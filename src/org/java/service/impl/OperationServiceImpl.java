package org.java.service.impl;

import org.java.dao.OperationMapper;
import org.java.entity.GoodsInfo;
import org.java.entity.IpShoppingCart;
import org.java.entity.UserCart;
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
    public UserCart getAllIpShoppingCartContent(String goodsId, String ip) {
        UserCart ipGoodsList = operationMapper.getAllIpShoppingCartContent(goodsId,ip);
        return ipGoodsList;
    }

    @Override
    public void updateLessIpShoppingCartGoodsNum(String goodsId, String ip) {
        operationMapper.updateLessIpShoppingCartGoodsNum(goodsId,ip);
    }

    @Override
    public void updateAddIpShoppingCartGoodsNum(String goodsId, String ip) {
        operationMapper.updateAddIpShoppingCartGoodsNum(goodsId, ip);
    }
}
