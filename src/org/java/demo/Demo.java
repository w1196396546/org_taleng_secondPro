package org.java.demo;

import org.java.dao.OperationMapper;
import org.java.dao.impl.OperationMapperImpl;
import org.java.entity.GoodsInfo;
import org.junit.Test;

import java.util.List;

public class Demo {

    @Test
    public void showGoodsInfo(){
        OperationMapper opm=new OperationMapperImpl();
        List<GoodsInfo> list=opm.getGoodsInfo();
        for (GoodsInfo good:list){
            System.out.println(good.getGoods_name());
        }
    }
}
