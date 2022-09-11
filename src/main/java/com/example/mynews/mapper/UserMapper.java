package com.example.mynews.mapper;

import com.example.mynews.entity.User;
import com.example.mynews.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface UserMapper {
    Integer insert(User user);
    User findByUsername(String username);
    UserVO findVOByUserName(String username);

    Integer updatePasswordByUid(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    User findByUid(Integer uid);
    UserVO findVOByUid(Integer uid);

    Integer updateInfoByUid(User user);

    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    Integer deleteByUid(Integer uid);
}
