package com.stk.mrkt.ojhastkmrktcompanydetailservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = { "com.stk.mrkt.ojhastkmrktcompanydetailservice" })
public class CompanyDetailSwaggerVersion {

	@Bean
	public Docket stkmrktDatasouceDocumentation() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.stk.mrkt.ojhastkmrktcompanydetailservice.controller"))
				.paths(PathSelectors.any()).build().pathMapping("/").apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Ojhas Company Detail Service").description(
				"Company detail Service is mainly for the loading/fetching the company details.")
				.build();
	}
}
