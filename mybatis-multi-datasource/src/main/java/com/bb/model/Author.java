package com.bb.model;

import lombok.Data;

import java.util.Date;

/**
 * @author BB
 * @create 2021/3/24
 */
@Data
public class Author {

    private int id;

    private String userName;

    private String password;

    private String bio;

    private Date createTime;

    private Date modifyTime;
}
