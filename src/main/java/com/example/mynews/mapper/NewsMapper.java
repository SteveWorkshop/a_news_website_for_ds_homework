package com.example.mynews.mapper;

import com.example.mynews.entity.News;
import com.example.mynews.vo.NewsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface NewsMapper {
    Integer insert(News news);
    List<News> getAll();
    List<NewsVO> getAllVO();
    NewsVO getById(int nid);
    List<News> getPage(@Param("start") int start,@Param("end") int end);
    List<NewsVO> getVOPage(@Param("start") int start, @Param("end") int end);
    List<NewsVO> getVOPageByTypeName(@Param("typeName")String typename ,@Param("start") int start,@Param("end") int end);
    List<NewsVO> getRandomVOByType(@Param("typename")String typename,@Param("num")int num);
    Integer getNewsCount(Date date);
    Integer updateInfoByNid(News news);
    Integer deleteByNid(Integer nid);
}
