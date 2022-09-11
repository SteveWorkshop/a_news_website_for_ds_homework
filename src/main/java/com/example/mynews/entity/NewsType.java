package com.example.mynews.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class NewsType extends BaseEntity implements Serializable {
    private Integer ntypeid;
    private String typeName;
}
