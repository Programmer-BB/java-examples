package com.bb.mapper.author;

import com.bb.model.Author;
import com.bb.model.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author BB
 * @create 2021/3/24
 */
public interface AuthorMapper {

    int insert(@Param("author") Author author);

    int deleteByPrimaryKey(@Param("id") int id);

    int updateByPrimaryKey(@Param("id") int id, @Param("author") Author author);

    /**
     * 根据ID获取Blog对象
     *
     * @param id 主键
     * @return
     */
    Author selectByPrimaryKey(int id);


    List<Author> selectList(@Param("author") Author author);

}
