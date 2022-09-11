package com.example.mynews.token;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class UserToken implements Serializable {
    private String tokenId;
    private String userName;
}
