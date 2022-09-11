package com.example.mynews.service;

import com.example.mynews.entity.News;
import com.example.mynews.vo.NewsVO;

import java.util.List;

public interface NewsService {
    List<NewsVO> getNewsByPage(int pageStart, int pageSize);
    NewsVO findById(int nid);
}
