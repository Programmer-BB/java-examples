package com.bb.mapper;

import com.bb.model.Blog;
import com.bb.util.SqlSessionFactoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 单表的增、删、改、查
 *
 * @author BB
 * @create 2021/3/20
 */
@Slf4j
public class BlogMapperTest {

    SqlSession session;

    @Before
    public void before() {
        Optional<SqlSession> opt = SqlSessionFactoryUtil.getSqlSession("mybatis-config.xml");
        opt.ifPresent(sqlSession -> session = sqlSession);
    }

    @Test
    public void testInsert() {
        try {
            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            int count = blogMapper.insert(Blog.builder()
                    .title("今天踢球")
                    .content("今天晚上6点老地方踢球.....")
                    .build());
            // 提交事务
            session.commit();
            assert count == 1;
        } catch (Exception e) {
            // 回滚事务
            session.rollback();
            log.error("insert异常", e);
        } finally {
            close(session);
        }
    }

    @Test
    public void testDeleteByPrimaryKey() {
        try {
            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            int count = blogMapper.deleteByPrimaryKey(1);
            session.commit();
            assert count == 1;
        } catch (Exception e) {
            session.rollback();
            log.error("delete异常", e);
        } finally {
            close(session);
        }
    }

    @Test
    public void testUpdateByPrimaryKey() {
        try {
            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            int count = blogMapper.updateByPrimaryKey(1, Blog.builder()
                    .content("今天晚上8点老地方踢球.....")
                    .build());
            // 提交事务
            session.commit();
            assert count == 1;
        } catch (Exception e) {
            // 回滚事务
            session.rollback();
            log.error("insert异常", e);
        } finally {
            close(session);
        }
    }

    @Test
    public void testSelectByPrimaryKey() {
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectByPrimaryKey(2);
        assert Objects.isNull(blog);
    }

    @Test
    public void testSelectList(){
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        List<Blog> blogs = blogMapper.selectList(Blog.builder().id(0).build());
        assert blogs.size() == 0;
    }

    /**
     * 关闭SqlSession
     *
     * @param sqlSession
     */
    private void close(SqlSession sqlSession) {
        if (Objects.nonNull(sqlSession)) {
            sqlSession.close();
        }
    }
}