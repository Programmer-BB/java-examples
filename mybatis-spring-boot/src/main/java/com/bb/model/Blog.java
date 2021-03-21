package com.bb.model;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author BB
 * @create 2021/3/21
 */
@Data
@Builder
@Alias("Blog")
public class Blog {

    /**
     * 编号，唯一
     */
    private int id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;
}
