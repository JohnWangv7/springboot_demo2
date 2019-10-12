package com.esen.swglpt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.esen.swglpt.mapper")
public class SwglptApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwglptApplication.class, args);
    }

}
