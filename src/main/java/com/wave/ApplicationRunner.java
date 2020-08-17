package com.wave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.wave.util",
        "com.wave.controller",
        "com.wave.service",
        "com.wave.repository",
        "com.wave.model",
        "com.wave.validator",
        "com.wave.entity",
})

@EntityScan("com.wave.entity")
@EnableAutoConfiguration
public class ApplicationRunner {

        public static void main(String[] args) {

            SpringApplication.run(ApplicationRunner.class, args);
        }

}
