package com.stk.mrkt.ojhastkmrktcompanydetailservice.model;

import java.util.List;

import lombok.Data;

@Data
public class CompanyDetailsRequest {
	
	private Long companyCode;
	private String companyName;
	private String companyCEO;
	private Double companyTurnover;
	private String companyWebsite;
	private List<String> stockExchangeList;
	
}
