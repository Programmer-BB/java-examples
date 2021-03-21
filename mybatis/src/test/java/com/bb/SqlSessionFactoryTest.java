package com.bb;

import com.bb.mapper.BlogMapper;
import com.bb.model.Blog;
import com.bb.util.DataSourceFactory;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * 构建SqlSessionFactory对象的几种方式
 *
 * @author BB
 * @create 2021/3/19
 */
public class SqlSessionFactoryTest {

    SqlSessionFactory sqlSessionFactory;

    /**
     * 通过XML文件来构建SqlSessionFactory对象
     *
     * @throws IOException
     */
    @Test
    public void buildSqlSessionFactoryByXML() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 通过编码的方式来构建SqlSessionFactory对象
     * 构建顺序如下：
     * DataSource & TransactionFactory -> Environment -> Configuration -> SqlSessionFactory -> SqlSession
     *
     * 注意：这种方式需要将Mapper接口类与xxxMapper.xml文件一定要放置在同一个package下
     *
     * @throws IOException
     */
    @Test
    public void buildSqlSessionFactoryByAPI() throws IOException {
        // 加载配置文件
        String resource = "db.properties";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        Properties properties = new Properties();
        properties.load(inputStream);
        // 创建数据源
        DataSource dataSource = DataSourceFactory.getDataSource("mysql", properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
        // 创建事务管理器
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        // 创建环境信息
        Environment environment = new Environment("mysql", transactionFactory, dataSource);
        // 创建配置信息
        Configuration configuration = new Configuration(environment);
        // 注意：如果xxxMapper.xml文件中resultType使用的是别名，则需要先注册别名; 如果是使用的包名+类名(未使用别名)，则不需要注册别名
        configuration.getTypeAliasRegistry().registerAlias("Blog", Blog.class);
        // 注册mapper
        configuration.addMapper(BlogMapper.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    /**
     * 通过编码的方式来构建SqlSessionFactory对象
     * 注意：这种方式xxxMapper.xml文件可以放置在任何路径下
     *
     * @throws IOException
     */
    @Test
    public void buildSqlSessionFactoryByAPI2() throws IOException {
        String dbResource = "db.properties";
        InputStream inputStream = Resources.getResourceAsStream(dbResource);
        Properties properties = new Properties();
        properties.load(inputStream);

        String xmlMapperResource = "mybatis-mapper/BlogMapper.xml";
        DataSource dataSource = DataSourceFactory.getDataSource("mysql", properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("mysql", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        // 注册别名
        configuration.getTypeAliasRegistry().registerAlias("Blog", Blog.class);
        // 手动解析xxxMapper.xml文件(绝对路径)
//        XMLMapperBuilder builder = new XMLMapperBuilder(new FileInputStream(xmlMapperResource), configuration, xmlMapperResource, new HashMap<>());

        // 手动解析xxxMapper.xml文件(相对路径)
        XMLMapperBuilder builder = new XMLMapperBuilder(Resources.getResourceAsStream(xmlMapperResource), configuration, xmlMapperResource, new HashMap<>());
        builder.parse();

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }
}