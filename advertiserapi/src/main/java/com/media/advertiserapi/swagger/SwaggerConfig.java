package com.media.advertiserapi.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("com.media.advertiserapi.rest")
public class SwaggerConfig {
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
		        .paths(Predicates.not(PathSelectors.regex("/error")))
		        .build()
		        .apiInfo(apiInfo());
    }
     
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Project AdvertiserAPI",
                                        "Goal of Project is to expose CRUD api for Advertiser",
                                        "1.0",
                                        "",
                                        new Contact("Advertiser", "", "kunal.gupta.2002@gmail.com"),
                                        "Apache License",
                                        "");
        return apiInfo;
    }

}