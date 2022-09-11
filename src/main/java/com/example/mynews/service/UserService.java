package com.example.mynews.service;

import com.example.mynews.entity.User;
import com.example.mynews.vo.UserVO;

public interface UserService {
    void register(User user);
    UserVO login(String username, String password);
    void changePassword(Integer uid, String username, String oldPassword, String newPassword);
    UserVO getByUid(Integer uid);
    void changeInfo(Integer uid, String username, UserVO uservo);
    void changeAvatar(Integer uid, String username, String avatar);
}
