package com.cy.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
//class没有配置datasource的一些相关属性,所以用上述语句声明

@MapperScan("com.cy.store.mapper")
//MapperScan注解指定当前项目中的mapper接口路径的位置，在项目启动的时候会自动加载所有接口文件
public class StoreApplication {

    public static void main(String[] args) {

        SpringApplication.run(StoreApplication.class, args);
    }

}
