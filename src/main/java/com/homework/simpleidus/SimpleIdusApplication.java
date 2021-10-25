package com.homework.simpleidus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SimpleIdusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleIdusApplication.class, args);
    }

}
