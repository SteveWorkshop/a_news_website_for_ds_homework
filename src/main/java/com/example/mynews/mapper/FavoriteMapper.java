package com.example.mynews.mapper;

import com.example.mynews.entity.Favorite;
import com.example.mynews.vo.FavoriteVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FavoriteMapper {
    Integer insert(Favorite favorite);
    FavoriteVO getVOByUid(Integer uid);
    Integer deleteByUidAndNid(@Param("uid")Integer uid,@Param("nid")Integer nid);
}
