package com.stk.mrkt.ojhastkmrktcompanydetailservice.service;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyDetailsRequest;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyDetailsResponse;

public interface CompanyDetailService {

	public CompanyDetailsResponse saveComapanyDetails(CompanyDetailsRequest request);
	
	public CompanyDetailsResponse deleteComapanyDetails(String companyCode);
}
