package com.demotuwei.demotuwei;

import com.demotuwei.demotuwei.netty.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.demotuwei.demotuwei.mapper")
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableAsync
@EnableCaching
@ServletComponentScan
@EnableScheduling
@EnableAspectJAutoProxy
public class DemotuweiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemotuweiApplication.class, args);
    }


    @Async("threadpool")
    @Override
    public void run(String... args) throws Exception {
        NettyServer.startNettyServer();
    }
}
