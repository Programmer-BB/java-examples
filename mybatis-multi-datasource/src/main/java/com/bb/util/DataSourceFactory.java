package com.bb.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例
 *
 * @author BB
 * @create 2021/3/20
 */
public class DataSourceFactory {

    private static Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>(16);

    public static DataSource getDataSource(String databaseName, String url, String userName, String password) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(userName);
        dataSource.setPassword(password);
        dataSourceMap.put(databaseName, dataSource);
        return dataSource;
    }

}
