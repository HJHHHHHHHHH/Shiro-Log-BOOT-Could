package com.control.page;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.control.page.dao")
@EnableSwagger2
@EnableEurekaClient
public class ControlPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlPageApplication.class, args);
    }

}

