package com.example.mynews.service.impl;

import com.example.mynews.entity.News;
import com.example.mynews.mapper.NewsMapper;
import com.example.mynews.mapper.NewsTypeMapper;
import com.example.mynews.service.NewsService;
import com.example.mynews.vo.NewsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsTypeMapper newsTypeMapper;

    @Override
    public List<NewsVO> getNewsByPage(int pageStart, int pageSize) {
        int start=(pageStart-1)*pageSize;
        int end=pageSize;
        return newsMapper.getVOPage(start,end);
    }

    @Override
    public NewsVO findById(int nid) {
        return newsMapper.getById(nid);
    }
}
