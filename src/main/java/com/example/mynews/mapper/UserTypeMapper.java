package com.example.mynews.mapper;

import com.example.mynews.entity.UserType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserTypeMapper {
    Integer insert(UserType userType);
    UserType findByUtypeid(@Param("utypeid") Integer utypeid);
    UserType findByTypeName(String typeName);
    List<UserType> findAll();
    Integer updateTypeNameByUtypeid(@Param("utypeid")Integer utypeid,
                               @Param("typeName")String typeName,
                               @Param("modifiedUser")String modifiedUser,
                               @Param("modifiedTime")Date modifiedTime);
}
