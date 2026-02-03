package com.test.springapp.presentation.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot 3 with Java 17!";
    }

    @GetMapping("/info")
    public String info() {
        return "Spring Boot Version: " + org.springframework.boot.SpringBootVersion.getVersion() +
               ", Java Version: " + System.getProperty("java.version");
    }
}
