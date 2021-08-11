package com.stk.mrkt.ojhastkmrktcompanydetailservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.dao.CompanyDetailsRepository;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.entity.CompanyDetailsEntity;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyDetailsRequest;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyDetailsResponse;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.service.CompanyDetailService;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.util.CompanyDetailResponseMapper;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.util.CompanyDetailsUtil;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailService {

	@Autowired
	CompanyDetailsRepository repository;

	@Autowired
	CompanyDetailResponseMapper mapper;

	@Autowired
	CompanyDetailsUtil util;

	@Override
	public CompanyDetailsResponse saveComapanyDetails(CompanyDetailsRequest request) {

		CompanyDetailsEntity entity = mapper.companyDeatilsRequestMapper(request);
		CompanyDetailsEntity result = repository.save(entity);
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		response.setCompanyCode(result.getCompanyCode().toString());
		response.setResponseID(util.getResponseId());
		response.setResponseMsg("data saved successfully");
		return response;
	}

	@Override
	public CompanyDetailsResponse deleteComapanyDetails(String companyCode) {
		Long companyId = Long.valueOf(companyCode);
		repository.deleteById(companyId);
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		response.setResponseID(util.getResponseId());
		response.setResponseMsg("data deleted successfully");
		return response;
	}

}
