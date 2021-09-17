package com.stk.mrkt.ojhastkmrktcompanydetailservice.model.response;

import java.util.List;

import lombok.Data;

@Data
public class CompanyDetailsResponse extends SuccessResponse {

	private String companyCode;
	private CompanyDetail companyDetail;
	private List<CompanyDetail> companyDetails;

}
