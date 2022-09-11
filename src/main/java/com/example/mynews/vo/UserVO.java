package com.example.mynews.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class UserVO implements Serializable {
    private Integer uid;
    private String typeName;
    private String username;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;
}
