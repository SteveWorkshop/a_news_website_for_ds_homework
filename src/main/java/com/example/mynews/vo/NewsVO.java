package com.example.mynews.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
public class NewsVO implements Serializable {
    private Integer nid;
    private String typeName;
    private String newsTitle;
    private String newsContent;
    private String newsImage;
    private String modifiedUser;
    private Date modifiedTime;
}
