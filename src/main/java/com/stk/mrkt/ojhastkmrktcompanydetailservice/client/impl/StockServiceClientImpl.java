package com.stk.mrkt.ojhastkmrktcompanydetailservice.client.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.client.StockServiceClient;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.exception.ClientServiceException;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.StockDetailResponse;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.response.ErrorResponse;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.util.CompanyDetailsUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockServiceClientImpl implements StockServiceClient {

	@Value("${stockservice.rest.baseuri}")
	private String baseuri;

	@Value("${stockservice.rest.resourceuri.deletecompanystocks}")
	private String deleteCompanyStocksUrl;
	
	@Value("${stockservice.rest.resourceuri.getallcompanystocks}")
	private String getAllCompanyStocks;
	
	@Value("${stockservice.rest.resourceuri.getcompanystocks}")
	private String getStocksByCompanyCode;
	
	@Value("${stockservice.rest.resourceuri.getstocksbyrange}")
	private String getStocksByRange;

	@Autowired
	CompanyDetailsUtil utils;

	@Autowired
	@Qualifier(value = "restTemplate")
	private RestTemplate restTemplate;

	@Override
	public StockDetailResponse deleteStocksOfComapny(String companyCode) {
		final String method = "StockServiceClientImpl::StockServiceClientImpl";
		long start = System.currentTimeMillis();
		StockDetailResponse response = null;
		logger.info("[{}] - Company Code : {}", method, companyCode);

		try {

			// Generating the REST API URL for a Stock Service - Delete stocks of a company
			String stockServiceUrl = new StringBuilder(baseuri).append(deleteCompanyStocksUrl).toString();

			HttpEntity<String> entity = new HttpEntity<>(utils.getHttpHeaders());
			Map<String, String> urlParams = new HashMap<>();
			urlParams.put("companycde", companyCode);

			String url = UriComponentsBuilder.fromUriString(stockServiceUrl).buildAndExpand(urlParams).toUriString();

			logger.info("[{}] - About to invoke stock service - {}", method, url);
			ResponseEntity<StockDetailResponse> result = restTemplate.exchange(url, HttpMethod.DELETE, entity,
					StockDetailResponse.class);

			response = result.getBody();

			// Validate Response
			if (response == null) {
				logger.error("[{}] - Response is null from Downstream", method);
				throw new ClientServiceException(CompanyDetailConstants.DOWNSTREAM_NULL_RESPONSE);
			}

			logger.info("[{}] - Received Response in - {}", method, start - System.currentTimeMillis());
			logger.debug("[{}] - Stock Service Response - {}", method, stockServiceUrl);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throwServiceInvocationException(e);
		} catch (Exception e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throw new ClientServiceException(CompanyDetailConstants.DOWNSTREAM_ERROR);
		}
		
		return response;

	}
	
	@Override
	public StockDetailResponse getAllCompanyStocks() {
		final String method = "StockServiceClientImpl::StockServiceClientImpl";
		long start = System.currentTimeMillis();
		StockDetailResponse response = null;

		try {

			// Generating the REST API URL for a Stock Service - Delete stocks of a company
			String stockServiceUrl = new StringBuilder(baseuri).append(getAllCompanyStocks).toString();

			HttpEntity<String> entity = new HttpEntity<>(utils.getHttpHeaders());

			String url = UriComponentsBuilder.fromUriString(stockServiceUrl).toUriString();

			logger.info("[{}] - About to invoke stock service - {}", method, url);
			ResponseEntity<StockDetailResponse> result = restTemplate.exchange(url, HttpMethod.GET, entity,
					StockDetailResponse.class);

			response = result.getBody();

			// Validate Response
			if (response == null) {
				logger.error("[{}] - Response is null from Downstream", method);
				throw new ClientServiceException(CompanyDetailConstants.DOWNSTREAM_NULL_RESPONSE);
			}

			logger.info("[{}] - Received Response in - {}", method, start - System.currentTimeMillis());
			logger.debug("[{}] - Stock Service Response - {}", method, stockServiceUrl);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throwServiceInvocationException(e);
		} catch (Exception e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throw new ClientServiceException(CompanyDetailConstants.DOWNSTREAM_ERROR);
		}
		
		return response;

	}
	
	@Override
	public StockDetailResponse getStockByCompanyCode(String companyCode) {
		final String method = "StockServiceClientImpl::StockServiceClientImpl";
		long start = System.currentTimeMillis();
		StockDetailResponse response = null;
		logger.info("[{}] - Company Code : {}", method, companyCode);

		try {

			// Generating the REST API URL for a Stock Service - Delete stocks of a company
			String stockServiceUrl = new StringBuilder(baseuri).append(getStocksByCompanyCode).toString();

			HttpEntity<String> entity = new HttpEntity<>(utils.getHttpHeaders());
			Map<String, String> urlParams = new HashMap<>();
			urlParams.put("companycde", companyCode);

			String url = UriComponentsBuilder.fromUriString(stockServiceUrl).buildAndExpand(urlParams).toUriString();

			logger.info("[{}] - About to invoke stock service - {}", method, url);
			ResponseEntity<StockDetailResponse> result = restTemplate.exchange(url, HttpMethod.GET, entity,
					StockDetailResponse.class);

			response = result.getBody();

			// Validate Response
			if (response == null) {
				logger.error("[{}] - Response is null from Downstream", method);
				throw new ClientServiceException(CompanyDetailConstants.DOWNSTREAM_NULL_RESPONSE);
			}

			logger.info("[{}] - Received Response in - {}", method, start - System.currentTimeMillis());
			logger.debug("[{}] - Stock Service Response - {}", method, stockServiceUrl);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throwServiceInvocationException(e);
		} catch (Exception e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throw new ClientServiceException(CompanyDetailConstants.DOWNSTREAM_ERROR);
		}
		
		return response;

	}
	
	@Override
	public StockDetailResponse getStocksByRange(String companyCode, String startDate, String endDate) {
		final String method = "StockServiceClientImpl::StockServiceClientImpl";
		long start = System.currentTimeMillis();
		StockDetailResponse response = null;
		logger.info("[{}] - Company Code : {}", method, companyCode);

		try {

			// Generating the REST API URL for a Stock Service - Delete stocks of a company
			String stockServiceUrl = new StringBuilder(baseuri).append(getStocksByRange).toString();

			HttpEntity<String> entity = new HttpEntity<>(utils.getHttpHeaders());
			Map<String, String> urlParams = new HashMap<>();
			urlParams.put("companycde", companyCode);
			urlParams.put("startdate", startDate);
			urlParams.put("enddate", endDate);

			String url = UriComponentsBuilder.fromUriString(stockServiceUrl).buildAndExpand(urlParams).toUriString();

			logger.info("[{}] - About to invoke stock service - {}", method, url);
			ResponseEntity<StockDetailResponse> result = restTemplate.exchange(url, HttpMethod.GET, entity,
					StockDetailResponse.class);

			response = result.getBody();

			// Validate Response
			if (response == null) {
				logger.error("[{}] - Response is null from Downstream", method);
				throw new ClientServiceException(CompanyDetailConstants.DOWNSTREAM_NULL_RESPONSE);
			}

			logger.info("[{}] - Received Response in - {}", method, start - System.currentTimeMillis());
			logger.debug("[{}] - Stock Service Response - {}", method, stockServiceUrl);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throwServiceInvocationException(e);
		} catch (Exception e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throw new ClientServiceException(CompanyDetailConstants.DOWNSTREAM_ERROR);
		}
		
		return response;

	}

	private void throwServiceInvocationException(HttpStatusCodeException e) {
		final String method = "StockServiceClientImpl::StockServiceClientImpl";
		
		HttpStatus status = e.getStatusCode();
		
		if (status == HttpStatus.NOT_FOUND) {
			logger.error("[{}] - failed due to wrong url", method);
			throw new ClientServiceException(CompanyDetailConstants.DOWNSTREAM_ERROR);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		ErrorResponse error = null;
		
		try {
			error = mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
		} catch (Exception e1) {
			logger.error("[{}] - Exception Occured - {}", method, e1);
			throw new ClientServiceException(CompanyDetailConstants.DOWNSTREAM_ERROR);
		}
		
		throw new ClientServiceException(error.getErrorCode().toString(), error.getErrorMessage(), status);
	}
}
