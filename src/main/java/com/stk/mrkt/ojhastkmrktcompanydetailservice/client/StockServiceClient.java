package com.stk.mrkt.ojhastkmrktcompanydetailservice.client;

import com.stk.mrkt.ojhastockdetailservice.model.StockDetailResponse;

public interface StockServiceClient {
	public StockDetailResponse deleteStocksOfComapny(String companyCode);

	public StockDetailResponse getAllCompanyStocks();

	public StockDetailResponse getStockByCompanyCode(String companyCode);

	public StockDetailResponse getStocksByRange(String companyCode, String startDate, String endDate);
}
