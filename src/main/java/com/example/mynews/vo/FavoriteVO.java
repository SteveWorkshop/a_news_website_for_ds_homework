package com.example.mynews.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
public class FavoriteVO implements Serializable {
    private Integer uid;
    private Integer nid;
    private String typeName;
    private String newsTitle;
    private Date createdTime;
}
