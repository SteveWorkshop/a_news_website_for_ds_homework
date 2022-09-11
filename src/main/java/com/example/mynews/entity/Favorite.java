package com.example.mynews.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
@EqualsAndHashCode
public class Favorite extends BaseEntity implements Serializable {
    public static final int CANCELED=1;
    public static final int VALID=0;

    private Integer uid;
    private Integer nid;
    private Integer isCanceled;
}
