package com.example.mynews.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class User extends BaseEntity implements Serializable {
    public static final int GIRL=0;
    public static final int BOY=1;
    public static final int DELETE=1;
    public static final int ACTIVE=0;

    private Integer uid;
    private Integer utypeid;
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;
}
