package com.stk.mrkt.ojhastkmrktcompanydetailservice.controller;

import static com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants.API_CONTEXT_ROOT;
import static com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants.COMPANY_DETAILS_CONTROLLER;
import static com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants.DELETE_COMPANY_DETAILS_URI;
import static com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants.FETCHALL_COMPANY_DETAILS_URI;
import static com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants.FETCH_COMPANY_DETAILS_URI;
import static com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants.FETCH_COMPANY_STOCKS_BY_RANGE_URI;
import static com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants.SAVE_COMPANY_DETAILS_URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.request.CompanyDetailsRequest;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.response.CompanyDetailsResponse;
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
@CrossOrigin(origins = "*", maxAge = 3600)
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
	//@PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> saveCompanyData(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@ApiParam(name = "CompanyDetailsRequest", value = "Request Body", required = true) @RequestBody @Valid CompanyDetailsRequest request) {
		logger.info("Company Detail started");
		CompanyDetailsResponse response = service.saveComapanyDetails(request);
		logger.info("Company Detail ended");
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = SAVE_COMPANY_DETAILS_URI, notes = "This API stores the company details in  DB")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Save Company Data", response = CompanyDetailsResponse.class) })
	@PutMapping(SAVE_COMPANY_DETAILS_URI)
	//@PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> editCompanyData(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@ApiParam(name = "CompanyDetailsRequest", value = "Request Body", required = true) @RequestBody @Valid CompanyDetailsRequest request) {
		logger.info("Company Detail started");
		CompanyDetailsResponse response = service.saveComapanyDetails(request);
		logger.info("Company Detail ended");
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = DELETE_COMPANY_DETAILS_URI, notes = "This API deletes the company details from  DB")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete Company Data", response = CompanyDetailsResponse.class) })
	@DeleteMapping(value = DELETE_COMPANY_DETAILS_URI, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.TEXT_PLAIN_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	//@PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> deleteCompanyData(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@ApiParam(name = "companycde", value = "companycde", required = true) @PathVariable(name="companycde", required = true) String companyCde) {
		CompanyDetailsResponse response = service.deleteComapanyDetails(companyCde);
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = FETCH_COMPANY_DETAILS_URI, notes = "This API fetches the comapny details for the requested company code")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Company Data", response = CompanyDetailsResponse.class) })
	@GetMapping(value = FETCH_COMPANY_DETAILS_URI, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.TEXT_PLAIN_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> getCompanyDataByComapnyCode(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@ApiParam(name = "companycde", value = "companycde", required = true) @PathVariable(name="companycde", required = true) String companyCde) {
		CompanyDetailsResponse response = service.getCompanyByCompanyCode(companyCde);
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = FETCHALL_COMPANY_DETAILS_URI, notes = "This API fetches all the registered company details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get all Company Data", response = CompanyDetailsResponse.class) })
	@GetMapping(value = FETCHALL_COMPANY_DETAILS_URI, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.TEXT_PLAIN_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> getAllCompanyData(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken) {
		CompanyDetailsResponse response = service.getAllCompany();
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = FETCH_COMPANY_STOCKS_BY_RANGE_URI, notes = "This API fetches the stock value of the requested company")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete Company Data", response = CompanyDetailsResponse.class) })
	@GetMapping(value = FETCH_COMPANY_STOCKS_BY_RANGE_URI, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.TEXT_PLAIN_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "HTTP_AUTH_TOKEN", value = "JWT token header", required = false, dataType = "String", paramType = "header") })
	public ResponseEntity<CompanyDetailsResponse> getStocksByRange(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@ApiParam(name = "companycde", value = "companycde", required = true) @PathVariable(name = "companycde", required = true) String companycde,
			@ApiParam(name = "startdate", value = "startdate", required = true) @PathVariable(name = "startdate", required = true) String startdate,
			@ApiParam(name = "enddate", value = "enddate", required = true) @PathVariable(name = "enddate", required = true) String enddate) {
		CompanyDetailsResponse response = service.getComanyStocksByRange(companycde, startdate, enddate);
		return ResponseEntity.ok(response);
	}
}
