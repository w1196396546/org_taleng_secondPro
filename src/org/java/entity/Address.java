package org.java.entity;

import java.io.Serializable;
import java.util.Iterator;

public class Address implements Serializable {

    private String addr_id;
    private String addr_username;
    private String addr_user_address;
    private String user_email;
    private String addr_tel;
    private String addr_code;
    private Integer addr_provinceId;
    private Integer addr_cityId;
    private Integer addr_areaId;

    public String getAddr_id() {
        return addr_id;
    }

    public void setAddr_id(String addr_id) {
        this.addr_id = addr_id;
    }

    public String getAddr_username() {
        return addr_username;
    }

    public void setAddr_username(String addr_username) {
        this.addr_username = addr_username;
    }

    public String getAddr_user_address() {
        return addr_user_address;
    }

    public void setAddr_user_address(String addr_user_address) {
        this.addr_user_address = addr_user_address;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getAddr_tel() {
        return addr_tel;
    }

    public void setAddr_tel(String addr_tel) {
        this.addr_tel = addr_tel;
    }

    public String getAddr_code() {
        return addr_code;
    }

    public void setAddr_code(String addr_code) {
        this.addr_code = addr_code;
    }

    public Integer getAddr_provinceId() {
        return addr_provinceId;
    }

    public void setAddr_provinceId(Integer addr_provinceId) {
        this.addr_provinceId = addr_provinceId;
    }

    public Integer getAddr_cityId() {
        return addr_cityId;
    }

    public void setAddr_cityId(Integer addr_cityId) {
        this.addr_cityId = addr_cityId;
    }

    public Integer getAddr_areaId() {
        return addr_areaId;
    }

    public void setAddr_areaId(Integer addr_areaId) {
        this.addr_areaId = addr_areaId;
    }
}
