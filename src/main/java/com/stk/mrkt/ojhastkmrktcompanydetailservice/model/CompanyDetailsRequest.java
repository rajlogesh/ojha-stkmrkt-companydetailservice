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
	public Long getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(Long companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCEO() {
		return companyCEO;
	}
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	public Double getCompanyTurnover() {
		return companyTurnover;
	}
	public void setCompanyTurnover(Double companyTurnover) {
		this.companyTurnover = companyTurnover;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public List<String> getStockExchangeList() {
		return stockExchangeList;
	}
	public void setStockExchangeList(List<String> stockExchangeList) {
		this.stockExchangeList = stockExchangeList;
	}
	
	
}
