package com.amorepacific.inmemorycache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InMemoryCacheApplication {

    public static void main(String[] args) {
        System.out.println("init application ! ");
        SpringApplication.run(InMemoryCacheApplication.class, args);
        System.out.println("execute success application ! ");
    }


}
