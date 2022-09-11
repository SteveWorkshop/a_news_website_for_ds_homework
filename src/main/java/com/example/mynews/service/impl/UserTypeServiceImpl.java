package com.example.mynews.service.impl;

import com.example.mynews.entity.UserType;
import com.example.mynews.mapper.UserTypeMapper;
import com.example.mynews.service.UserTypeService;
import com.example.mynews.service.ex.InsertException;
import com.example.mynews.service.ex.NoSuchUserTypeException;
import com.example.mynews.service.ex.UpdateException;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeMapper userTypeMapper;

    @Override
    public List<UserType> findAll() {
        List<UserType> userTypes= userTypeMapper.findAll();
        for(var userType:userTypes)
        {
            userType.setCreatedUser(null);
            userType.setCreatedTime(null);
            userType.setModifiedUser(null);
            userType.setModifiedTime(null);
        }
        return userTypes;
    }

    @Override
    public UserType findById(Integer id) {
        UserType userType= userTypeMapper.findByUtypeid(id);
        if(userType==null)
        {
            throw new NoSuchUserTypeException("不存在的用户类型");
        }
        userType.setCreatedUser(null);
        userType.setCreatedTime(null);
        userType.setModifiedUser(null);
        userType.setModifiedTime(null);
        return userType;
    }

    @Override
    public void addType(String typeName,String user) {
        UserType userType=new UserType();
        userType.setTypeName(typeName);
        Date now=new Date();

        userType.setCreatedUser(user);
        userType.setCreatedTime(now);
        userType.setModifiedUser(user);
        userType.setModifiedTime(now);

        Integer rows=userTypeMapper.insert(userType);
        if(rows!=1)
        {
            throw new InsertException("服务器端错误！请坐和放宽，滚回功率，我们正在调查此问题，将在未来的构建修复");
        }
    }

    @Override
    public void changeInfo(Integer utypeid,String newTypeName,String username) {
        UserType result= userTypeMapper.findByUtypeid(utypeid);
        if(result==null)
        {
            throw new NoSuchUserTypeException("不存在的用户类型");
        }
        Date now=new Date();
        int rows= userTypeMapper.updateTypeNameByUtypeid(utypeid,newTypeName,username,now);
        if(rows!=1)
        {
            throw new UpdateException("服务器端错误！请坐和放宽，滚回功率，我们正在调查此问题，将在未来的构建修复");
        }
    }
}
