package com.stk.mrkt.ojhastkmrktcompanydetailservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.util.CompanyDetailResponseMapper;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.util.CompanyDetailsUtil;

@Configuration
public class CompanyDetailConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CompanyDetailsUtil companyDetailsUtil() {
		return new CompanyDetailsUtil();
	}
	
	@Bean
	CompanyDetailResponseMapper companyDetailResponseMapper() {
		return new CompanyDetailResponseMapper();
	}
}
