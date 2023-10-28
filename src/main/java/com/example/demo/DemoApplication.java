package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WebProperties.class)
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("Hello");
		SpringApplication.run(DemoApplication.class, args);
	}

}
