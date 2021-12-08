package org.java.entity;

public class Order {

    private Integer iid;

    private Integer cid;

    private Integer oid;

    private Integer count;

    private double subtoal;

    private String state;

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getSubtoal() {
        return subtoal;
    }

    public void setSubtoal(double subtoal) {
        this.subtoal = subtoal;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
