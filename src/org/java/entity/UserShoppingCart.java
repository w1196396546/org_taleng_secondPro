package org.java.entity;

public class UserShoppingCart {

    private String userShoppingId;

    private String userId;

    private String goodsId;

    private Integer goodsNum;

    public String getUserShoppingId() {
        return userShoppingId;
    }

    public void setUserShoppingId(String userShoppingId) {
        this.userShoppingId = userShoppingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }
}
