package com.group.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        DataBaseConnector.connect();
        SpringApplication.run(AppApplication.class, args);
    }

}
