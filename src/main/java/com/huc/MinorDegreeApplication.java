package com.huc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.huc.dao")
public class MinorDegreeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MinorDegreeApplication.class);
    }
}
