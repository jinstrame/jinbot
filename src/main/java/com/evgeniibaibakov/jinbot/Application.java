package com.evgeniibaibakov.jinbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.evgeniibaibakov.jinbot")
@PropertySource("classpath:botconfig.properties")
@EnableScheduling
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
