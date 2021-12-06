package org.java.dao;

import org.apache.ibatis.annotations.Param;

public interface RegisterMapper {

    void addUser(@Param("userEmail") String userEmail, @Param("userPwd") String userPwd);
}
