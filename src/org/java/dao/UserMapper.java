package org.java.dao;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int getCount(@Param("email") String userEmail);
}
