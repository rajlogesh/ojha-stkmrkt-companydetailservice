package com.stk.mrkt.ojhastkmrktcompanydetailservice.model.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.handler.InputValidateData;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CompanyDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long companyCode;

	@InputValidateData(required = CompanyDetailConstants.STRING_Y, regex = CompanyDetailConstants.ALPHA_NUMERIC_REGEX, max = 255)
	private String companyName;

	@InputValidateData(required = CompanyDetailConstants.STRING_Y, regex = CompanyDetailConstants.ALPHA_REGEX, max = 100)
	private String companyCEO;

	@InputValidateData(required = CompanyDetailConstants.STRING_Y, regex = CompanyDetailConstants.NUMERIC_DECIMEL_REGEX)
	private BigDecimal companyTurnover;

	@InputValidateData(required = CompanyDetailConstants.STRING_Y, regex = CompanyDetailConstants.WEBSITE_REGEX)
	private String companyWebsite;

	private List<String> stockExchangeList;

}
