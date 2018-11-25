package com.springkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(Binding.class)
public class SpringKafkaPipelineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaPipelineApplication.class, args);
    }
}
