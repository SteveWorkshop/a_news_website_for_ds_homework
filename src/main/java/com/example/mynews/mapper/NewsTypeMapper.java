package com.example.mynews.mapper;

import com.example.mynews.entity.NewsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface NewsTypeMapper {
    Integer insert(NewsType newsType);
    NewsType findByNtypeid(Integer ntypeid);
    List<NewsType> findAll();
    Integer updateTypeNameByNtypeid(@Param("ntypeid")Integer ntypeid,
                                    @Param("typeName")String typeName,
                                    @Param("modifiedUser")String modifiedUser,
                                    @Param("modifiedTime") Date modifiedTime);
}
