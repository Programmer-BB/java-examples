package com.bb.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author BB
 * @create 2021/3/20
 */
@Slf4j
public class SqlSessionFactoryUtil {

    private static SqlSessionFactory factory;

    private static void buildSqlSessionFactory(String resource) {
        try {
            factory = new SqlSessionFactoryBuilder().build(getInputStream(resource));
        } catch (Exception e) {
            log.error("创建SqlSession异常", e);
        }
    }

    public synchronized static SqlSession openSession(String resource) {
        if (Objects.isNull(factory)) {
            buildSqlSessionFactory(resource);
        }
        return factory.openSession();
    }

    private static InputStream getInputStream(String resource) throws IOException {
        return Resources.getResourceAsStream(resource);
    }
}
