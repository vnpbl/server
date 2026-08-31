package com.pablo.cafe;

import com.pablo.cafe.controller.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CafeMSApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(CafeMSApplication.class, args);

    }
}
