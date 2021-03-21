package com.bb.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author BB
 * @create 2021/3/20
 */
@Slf4j
public class SqlSessionFactoryUtil {

    public static Optional<SqlSession> getSqlSession(String resource) {
        try {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(getInputStream(resource));
            return Optional.ofNullable(factory.openSession());
        } catch (Exception e) {
            log.error("创建SqlSession异常", e);
        }
        return Optional.empty();
    }

    private static InputStream getInputStream(String resource) throws IOException {
        return Resources.getResourceAsStream(resource);
    }
}
