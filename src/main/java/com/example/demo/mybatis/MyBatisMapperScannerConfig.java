package com.example.demo.mybatis;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * Created by luck on 17/6/24
 */
@Configuration
//必须在MyBatisConfig注册后再加载MapperScannerConfigurer，否则会报错
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
    mapperScannerConfigurer.setBasePackage("com.example.demo.mybatis");

    //初始化扫描器的相关配置，这里我们要创建一个Mapper的父类
    Properties properties = new Properties();
    properties.setProperty("mappers", "com.example.demo.config.MyMapper");
    properties.setProperty("notEmpty", "false");
    properties.setProperty("IDENTITY", "MYSQL");

    mapperScannerConfigurer.setProperties(properties);

    return mapperScannerConfigurer;
    }
}
