package com.stk.mrkt.ojhastkmrktcompanydetailservice.serviceimpl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.dao.CompanyDetailsRepository;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.entity.CompanyDetailsEntity;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyDetailsRequest;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyDetailsResponse;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.service.CompanyDetailService;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailService {

	@Autowired
	CompanyDetailsRepository repository;
	
	@Override
	public CompanyDetailsResponse saveComapanyDetails(CompanyDetailsRequest request) {
		
		CompanyDetailsEntity entity = companyDeatilsRequestMapper(request);
		CompanyDetailsEntity result = repository.save(entity);
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		response.setCompanyCode(result.getCompanyCode().toString());
		response.setResponseID("123456");
		response.setResponseMsg("data saved successfully");
		return response;
	}
		
	private CompanyDetailsEntity companyDeatilsRequestMapper(CompanyDetailsRequest request) {
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

	@Override
	public CompanyDetailsResponse deleteComapanyDetails(String companyCode) {
		Long companyId = Long.valueOf(companyCode);
		repository.deleteById(companyId);
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		response.setResponseID("123456");
		response.setResponseMsg("data updated successfully");
		return response;
	}

}
