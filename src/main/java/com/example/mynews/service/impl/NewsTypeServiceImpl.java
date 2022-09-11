package com.example.mynews.service.impl;

import com.example.mynews.entity.News;
import com.example.mynews.entity.NewsType;
import com.example.mynews.mapper.NewsTypeMapper;
import com.example.mynews.service.NewsTypeService;
import com.example.mynews.service.ex.InsertException;
import com.example.mynews.service.ex.NoSuchNewsTypeException;
import com.example.mynews.service.ex.UpdateException;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsTypeServiceImpl implements NewsTypeService {

    @Autowired
    private NewsTypeMapper newsTypeMapper;

    @Override
    public List<NewsType> findAll() {
        List<NewsType> newsTypeList= newsTypeMapper.findAll();
        for(var type:newsTypeList)
        {
            type.setCreatedUser(null);
            type.setCreatedTime(null);
            type.setModifiedUser(null);
            type.setModifiedTime(null);
        }

        return newsTypeList;
    }

    @Override
    public NewsType findById(Integer id) {
        NewsType newsType= newsTypeMapper.findByNtypeid(id);

        if(newsType==null)
        {
            throw new NoSuchNewsTypeException("不存在的新闻类型");
        }
        newsType.setCreatedUser(null);
        newsType.setCreatedTime(null);
        newsType.setModifiedUser(null);
        newsType.setModifiedTime(null);
        return newsType;
    }

    @Override
    public void addType(String typeName,String user) {
        NewsType newsType=new NewsType();
        Date now=new Date();

        newsType.setTypeName(typeName);
        newsType.setCreatedUser(user);
        newsType.setCreatedTime(now);
        newsType.setModifiedUser(user);
        newsType.setModifiedTime(now);

        int rows=newsTypeMapper.insert(newsType);
        if(rows!=1)
        {
            throw new InsertException("服务器端错误！请坐和放宽，滚回功率，我们正在调查此问题，将在未来的构建修复");
        }
    }

    @Override
    public void changeInfo(Integer ntypeid, String newTypeName, String username) {
        NewsType result=newsTypeMapper.findByNtypeid(ntypeid);
        if(result==null)
        {
            throw new NoSuchNewsTypeException("不存在的类型");
        }
        Date now=new Date();
        int rows=newsTypeMapper.updateTypeNameByNtypeid(ntypeid,newTypeName,username,now);
        if(rows!=1)
        {
            throw new UpdateException("服务器端错误！请坐和放宽，滚回功率，我们正在调查此问题，将在未来的构建修复");
        }
    }
}
