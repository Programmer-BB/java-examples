package com.bb.mapper;

import com.bb.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;

/**
 * 测试
 *
 * @author BB
 * @create 2021/3/21
 */
@Slf4j
public class BlogMapperTest {

    BlogMapper blogMapper;

    @Before
    public void before() {
        // 加载spring.xml文件，初始化IOC容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // 从IOC容器中获取Bean对象
        blogMapper = applicationContext.getBean("blogMapper", BlogMapper.class);
    }

    @Test
    public void insert() {
        try {
            int count = blogMapper.insert(Blog.builder()
                    .title("今天踢球")
                    .content("今天晚上6点老地方踢球.....")
                    .build());
            assert count == 1;
        } catch (Exception e) {
            log.error("insert异常", e);
        }
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectByPrimaryKey() {
        Blog blog = blogMapper.selectByPrimaryKey(2);
        assert Objects.nonNull(blog);
    }

    @Test
    public void selectList() {
    }
}