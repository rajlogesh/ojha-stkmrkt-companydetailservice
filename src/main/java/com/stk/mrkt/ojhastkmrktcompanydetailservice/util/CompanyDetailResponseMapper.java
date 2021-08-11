package com.stk.mrkt.ojhastkmrktcompanydetailservice.util;

import java.util.stream.Collectors;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.entity.CompanyDetailsEntity;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyDetailsRequest;

public class CompanyDetailResponseMapper {

	public CompanyDetailsEntity companyDeatilsRequestMapper(CompanyDetailsRequest request) {
		CompanyDetailsEntity entity = new CompanyDetailsEntity();
		entity.setCompanyCode(request.getCompanyCode());
		entity.setCompanyCEO(request.getCompanyCEO());
		entity.setCompanyName(request.getCompanyName());
		entity.setCompanyTurnover(request.getCompanyTurnover());
		entity.setCompanyWebsite(request.getCompanyWebsite());
		entity.setStockExchangeList(request.getStockExchangeList().stream().collect(Collectors.joining(",")));
		//entity.setCreationDate(new Date());
		return entity;
	}
	
}
