package com.zjdex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EccApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext cat =  SpringApplication.run(EccApplication.class, args);
        cat.close();
    }
}
