package com.stk.mrkt.ojhastkmrktcompanydetailservice.service;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.request.CompanyDetailsRequest;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.response.CompanyDetailsResponse;

public interface CompanyDetailService {

	public CompanyDetailsResponse saveComapanyDetails(CompanyDetailsRequest request);
	
	public CompanyDetailsResponse editComapanyDetails(CompanyDetailsRequest request);
	
	public CompanyDetailsResponse deleteComapanyDetails(String companyCode);

	public CompanyDetailsResponse getCompanyByCompanyCode(String companyCde);

	public CompanyDetailsResponse getAllCompany();

	public CompanyDetailsResponse getComanyStocksByRange(String companycde, String startdate, String enddate);
}
