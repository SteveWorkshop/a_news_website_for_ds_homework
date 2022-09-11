package com.example.mynews.service;

import com.example.mynews.entity.UserType;

import java.util.List;

public interface UserTypeService {
    List<UserType> findAll();
    UserType findById(Integer id);
    void addType(String typeName,String user);
    void changeInfo(Integer utypeid,String newTypeName,String username);
}
