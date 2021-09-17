package com.stk.mrkt.ojhastkmrktcompanydetailservice.serviceimpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.client.StockServiceClient;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.dao.CompanyDetailsRepository;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.entity.CompanyDetailsEntity;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.exception.InputValidationException;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.request.CompanyDetailsRequest;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.response.CompanyDetail;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.response.CompanyDetailsResponse;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.service.CompanyDetailService;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.util.CompanyDetailResponseMapper;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.util.CompanyDetailsUtil;
import com.stk.mrkt.ojhastockdetailservice.model.StockDetailEntity;
import com.stk.mrkt.ojhastockdetailservice.model.StockDetailResponse;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailService {

	@Autowired
	CompanyDetailsRepository repository;

	@Autowired
	StockServiceClient client;

	@Autowired
	CompanyDetailResponseMapper mapper;

	@Autowired
	CompanyDetailsUtil util;

	@Override
	public CompanyDetailsResponse saveComapanyDetails(CompanyDetailsRequest request) {
		boolean updateFlag = false;

		// Validate few parameters in the request
		validateCompanyTurnOver(request.getCompanyTurnover());
		validateStockExchangeList(request.getStockExchangeList());

		// mapper to map request to entity object
		CompanyDetailsEntity entity = mapper.companyDeatilsRequestMapper(request, updateFlag);

		// Call to DAO layer
		CompanyDetailsEntity result = repository.save(entity);

		// Form the response
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		response.setCompanyCode(result.getCompanyCode().toString());
		response.setResponseID(util.getResponseId());
		response.setResponseMsg("data saved successfully");

		return response;
	}

	@Override
	public CompanyDetailsResponse editComapanyDetails(CompanyDetailsRequest request) {
		boolean updateFlag = true;

		// Validate few parameters in the request
		validateCompanyTurnOver(request.getCompanyTurnover());
		validateStockExchangeList(request.getStockExchangeList());

		// mapper to map request to entity object
		CompanyDetailsEntity entity = mapper.companyDeatilsRequestMapper(request, updateFlag);

		// Call to DAO layer
		CompanyDetailsEntity result = repository.save(entity);

		// Form the response
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		response.setCompanyCode(result.getCompanyCode().toString());
		response.setResponseID(util.getResponseId());
		response.setResponseMsg("data saved successfully");

		return response;
	}
	
	private void validateStockExchangeList(List<String> stockExchangeList) {
		if (stockExchangeList == null || stockExchangeList.isEmpty()) {
			throw new InputValidationException(CompanyDetailConstants.INPUT_FIELD_REQUIRED, "stockExchangeList");
		}

		for (String financeMarket : stockExchangeList) {
			if (!CompanyDetailConstants.FINANCE_MARKET_LIST.contains(financeMarket)) {
				throw new InputValidationException(CompanyDetailConstants.COMPANY_STOCKLIST_ELIGIBILITY);
			}
		}

	}

	private void validateCompanyTurnOver(BigDecimal companyTurnOver) {
		if (companyTurnOver == null || companyTurnOver.compareTo(BigDecimal.ZERO) == 0) {
			throw new InputValidationException(CompanyDetailConstants.INPUT_FIELD_REQUIRED, "companyTurnOver");
		}

		if (companyTurnOver.compareTo(new BigDecimal(100000000)) < 0) {
			throw new InputValidationException(CompanyDetailConstants.COMPANY_TURNOVER_ELIGIBILITY);
		}

	}

	@Override
	public CompanyDetailsResponse deleteComapanyDetails(String companyCode) {
		Long companyId = Long.valueOf(companyCode);
		
		boolean companyExists = repository.existsById(companyId);
		
		if(!companyExists)
			throw new InputValidationException(CompanyDetailConstants.INVALID_COMPANY_CODE);
		
		client.deleteStocksOfComapny(companyCode);
		repository.deleteById(companyId);
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		response.setResponseID(util.getResponseId());
		response.setResponseMsg("data deleted successfully");
		return response;
	}

	@Override
	public CompanyDetailsResponse getCompanyByCompanyCode(String companyCde) {
		Long companyId = Long.valueOf(companyCde);
		
		CompanyDetailsEntity result = repository.findById(companyId).orElseThrow(() -> new InputValidationException(CompanyDetailConstants.INVALID_COMPANY_CODE));
		StockDetailResponse stockResult = client.getStockByCompanyCode(companyCde);
		
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		CompanyDetail companyDetail = mapper.companyDetailsResponseMapper(result, stockResult.getStockDetails());
		response.setResponseID(util.getResponseId());
		response.setCompanyDetail(companyDetail);
		response.setResponseMsg("fetched data successfully");
		return response;
	}

	@Override
	public CompanyDetailsResponse getAllCompany() {
		List<CompanyDetailsEntity> result = repository.findAll();
		StockDetailResponse stockResult = client.getAllCompanyStocks();
		
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		List<CompanyDetail> companyDetails = mapper.getAllCompanyResponseMapper(result, stockResult);
		response.setResponseID(util.getResponseId());
		response.setCompanyDetails(companyDetails);
		response.setResponseMsg("fetched data successfully");
		return response;
	}

	@Override
	public CompanyDetailsResponse getComanyStocksByRange(String companycde, String startdate, String enddate) {
		Long companyId = Long.valueOf(companycde);
		
		CompanyDetailsEntity result = repository.findById(companyId).orElseThrow(() -> new InputValidationException(CompanyDetailConstants.INVALID_COMPANY_CODE));
		StockDetailResponse stockResult = client.getStocksByRange(companycde, startdate, enddate);
		
		CompanyDetailsResponse response = new CompanyDetailsResponse();
		CompanyDetail companyDetail = mapper.companyDetailsResponseMapper(result, stockResult.getStockDetails());
		if(stockResult != null && stockResult.getStockDetails() != null && !stockResult.getStockDetails().isEmpty()) {
			companyDetail.setMaxStockPrice(stockResult.getStockDetails().stream().mapToDouble(StockDetailEntity::getStockPrice).max().getAsDouble());
			companyDetail.setMinStockPrice(stockResult.getStockDetails().stream().mapToDouble(StockDetailEntity::getStockPrice).min().getAsDouble());
			companyDetail.setAvgStockPrice(stockResult.getStockDetails().stream().collect(Collectors.averagingDouble(StockDetailEntity::getStockPrice)));
		}
		response.setResponseID(util.getResponseId());
		response.setCompanyDetail(companyDetail);
		response.setResponseMsg("fetched data successfully");
		return response;
	}

}
