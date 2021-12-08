package org.java.entity;

public class UserCart {

    private String goods_id;
    private String goods_name;
    private double goods_price;
    private String goods_class_id;
    private String goods_intro;
    private String goods_imgaddr;
    private int goods_count;
    private String goods_state;
    private Integer goods_num;

    public Integer getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(Integer goods_num) {
        this.goods_num = goods_num;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_class_id() {
        return goods_class_id;
    }

    public void setGoods_class_id(String goods_class_id) {
        this.goods_class_id = goods_class_id;
    }

    public String getGoods_intro() {
        return goods_intro;
    }

    public void setGoods_intro(String goods_intro) {
        this.goods_intro = goods_intro;
    }

    public String getGoods_imgaddr() {
        return goods_imgaddr;
    }

    public void setGoods_imgaddr(String goods_imgaddr) {
        this.goods_imgaddr = goods_imgaddr;
    }

    public int getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(int goods_count) {
        this.goods_count = goods_count;
    }

    public String getGoods_state() {
        return goods_state;
    }

    public void setGoods_state(String goods_state) {
        this.goods_state = goods_state;
    }
}
