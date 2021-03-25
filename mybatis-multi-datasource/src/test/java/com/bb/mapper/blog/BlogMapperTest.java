package com.bb.mapper.blog;

import com.bb.model.Blog;
import com.bb.util.SqlSessionFactoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

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
        session = SqlSessionFactoryUtil.openSession("mybatis-db1-config.xml");
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
            int count = blogMapper.updateByPrimaryKey(2, Blog.builder()
                    .title("测试多个参数")
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
    public void testSelectList() {
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        List<Blog> blogs = blogMapper.selectList(Blog.builder().id(0).build());
        assert blogs.size() == 0;
    }

    /**
     * 一级缓存测试
     * mybatis默认开启一级缓存，范围是：session级别，也就是同一个session内同样的查询条件的查询结果会被缓存
     */
    @Test
    public void testQueryCache(){
        SqlSession sqlSession1 = SqlSessionFactoryUtil.openSession("mybatis-db1-config.xml");
        BlogMapper blogMapper = sqlSession1.getMapper(BlogMapper.class);
        Blog queryBlog = Blog.builder().id(2).build();
        // 查询数据库
        List<Blog> blogs1 = blogMapper.selectList(queryBlog);
        // 查询缓存
        List<Blog> blogs2 = blogMapper.selectList(queryBlog);

        SqlSession sqlSession2 = SqlSessionFactoryUtil.openSession("mybatis-db1-config.xml");
        BlogMapper blogMapper2 = sqlSession2.getMapper(BlogMapper.class);
        // 查询数据库
        List<Blog> blogs3 = blogMapper2.selectList(queryBlog);
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