package com.example.mynews.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class News extends BaseEntity implements Serializable {
    public static final int DELETE=1;
    public static final int SHOW=0;

    private Integer nid;
    private Integer ntypeid;
    private String newsTitle;
    private String newsContent;
    private String newsImage;
    private Integer isDelete;
}
