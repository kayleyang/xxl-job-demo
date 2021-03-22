package com.chtwm.xxl.job.demo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class XxlJobDemoApplication {

    public static void main(String[] args) throws InterruptedException {
        new SpringApplicationBuilder(XxlJobDemoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        Thread.currentThread().join();
    }

}
