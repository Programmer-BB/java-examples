package com.bb;

import com.bb.mapper.BlogMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author BB
 * @create 2021/3/21
 */
@SpringBootApplication
@MapperScan(basePackages = "com.bb.mapper")
public class MybatisSpringBootApplication implements CommandLineRunner {

    BlogMapper blogMapper;

    public MybatisSpringBootApplication(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisSpringBootApplication.class);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.blogMapper.selectByPrimaryKey(6));
    }
}
