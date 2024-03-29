package com.example.springunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ServletComponentScan("com.example.springunity.filter")
public class SpringUnityApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SpringUnityApplication.class, args);
    }

}
