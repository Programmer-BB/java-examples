package com.bb.mapper.author;

import com.bb.model.Author;
import com.bb.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

/**
 * @author BB
 * @create 2021/3/24
 */
public class AuthorMapperTest {

    SqlSession session;

    @Before
    public void getSqlSession() {
        session = SqlSessionFactoryUtil.openSession("mybatis-db2-config.xml");
    }

    @Test
    public void insert() {
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectByPrimaryKey() {
        AuthorMapper mapper = session.getMapper(AuthorMapper.class);
        Author author = mapper.selectByPrimaryKey(1);
        assert Objects.isNull(author);
    }

    @Test
    public void selectList() {
    }
}