package com.stk.mrkt.ojhastkmrktcompanydetailservice.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.client.StockServiceClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockServiceClientImpl implements StockServiceClient {

	@Value("stockservice.rest.baseuri")
	private String baseuri;
	
	@Value("stockservice.rest.resourceuri.deletecompanystocks")
	private String deleteCompanyStocksUrl;
	
	@Autowired
	@Qualifier(value = "restTemplate")
	private RestTemplate restTemplate;
	
	//private S
}
