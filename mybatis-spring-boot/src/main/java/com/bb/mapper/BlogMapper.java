package com.bb.mapper;

import com.bb.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author BB
 * @create 2021/3/21
 */
@Mapper
public interface BlogMapper {

    int insert(@Param("blog") Blog blog);

    int deleteByPrimaryKey(@Param("id") int id);

    int updateByPrimaryKey(@Param("id") int id, @Param("blog") Blog blog);

    /**
     * 根据ID获取Blog对象
     *
     * @param id 主键
     * @return
     */
    Blog selectByPrimaryKey(@Param("id") int id);


    List<Blog> selectList(@Param("blog") Blog blog);
}
