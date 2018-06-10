package com.media.advertiserapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This is the main Spring Boot application
 * 
 * @author kugupta
 *
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableSwagger2
@ComponentScan(basePackages = "com.media.advertiserapi")
public class Application {
	private static final Class<Application> applicationClass = Application.class;

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

}
