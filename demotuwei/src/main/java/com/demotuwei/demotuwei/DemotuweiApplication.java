package com.demotuwei.demotuwei;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.demotuwei.demotuwei.mapper")
@EnableTransactionManagement
public class DemotuweiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemotuweiApplication.class, args);
    }





}
