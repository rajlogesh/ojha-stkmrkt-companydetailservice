package com.stk.mrkt.ojhastkmrktcompanydetailservice.controller;

import static com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants.API_CONTEXT_ROOT;
import static com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants.COMPANY_DETAILS_CONTROLLER;
import static com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants.DELETE_COMPANY_DETAILS_URI;
import static com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants.SAVE_COMPANY_DETAILS_URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyDetailsRequest;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyDetailsResponse;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.service.CompanyDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = API_CONTEXT_ROOT, produces = { MediaType.APPLICATION_JSON_VALUE, }, consumes = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(value = COMPANY_DETAILS_CONTROLLER, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CompanyDetailsController {

	@Autowired
	CompanyDetailService service;

	@ApiOperation(value = SAVE_COMPANY_DETAILS_URI, notes = "This API stores the company details in  DB")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Save Company Data", response = CompanyDetailsResponse.class) })
	@PostMapping(SAVE_COMPANY_DETAILS_URI)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> saveCompanyData(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@ApiParam(name = "CompanyDetailsRequest", value = "Request Body", required = true) @RequestBody CompanyDetailsRequest request) {
		CompanyDetailsResponse response = service.saveComapanyDetails(request);
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = DELETE_COMPANY_DETAILS_URI, notes = "This API deletes the company details from  DB")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete Company Data", response = CompanyDetailsResponse.class) })
	@DeleteMapping(value = DELETE_COMPANY_DETAILS_URI, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.TEXT_PLAIN_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> deleteCompanyData(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@ApiParam(name = "companycde", value = "companycde", required = true) @PathVariable(name="companycde", required = true) String companyCde) {
		CompanyDetailsResponse response = service.deleteComapanyDetails(companyCde);
		return ResponseEntity.ok(response);
	}
}
