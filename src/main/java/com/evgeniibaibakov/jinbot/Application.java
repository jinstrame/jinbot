package com.evgeniibaibakov.jinbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.evgeniibaibakov.jinbot")
@PropertySource("classpath:botconfig.properties")
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
