package com.example.mynews.service;

import com.example.mynews.entity.NewsType;

import java.util.List;

public interface NewsTypeService {
    List<NewsType> findAll();
    NewsType findById(Integer id);
    void addType(String typeName,String user);
    void changeInfo(Integer ntypeid,String newTypeName,String username);
}
