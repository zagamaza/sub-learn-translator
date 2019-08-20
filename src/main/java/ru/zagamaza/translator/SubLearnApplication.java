package ru.zagamaza.translator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SubLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubLearnApplication.class, args);
    }

}
