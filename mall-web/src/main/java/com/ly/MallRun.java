package com.ly;

import  org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YiMeng
 * @DateTime: 2022/8/4 9:55
 * @Description: TODO
 */
@SpringBootApplication
@MapperScan(basePackages = "com.ly.mapper")
public class MallRun {
    public static void main(String[] args) {

        SpringApplication.run(MallRun.class);
    }
}
