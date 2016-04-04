package com.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author rohat
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan()
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
