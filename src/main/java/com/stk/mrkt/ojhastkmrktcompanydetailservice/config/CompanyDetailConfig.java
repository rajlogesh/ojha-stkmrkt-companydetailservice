package com.stk.mrkt.ojhastkmrktcompanydetailservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.service.CompanyDetailService;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.serviceimpl.CompanyDetailsServiceImpl;

@Configuration
public class CompanyDetailConfig {
	
	@Bean
	public CompanyDetailService companyDetailService() {
		return new CompanyDetailsServiceImpl();
	}
	
}
