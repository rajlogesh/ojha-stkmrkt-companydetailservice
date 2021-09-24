package com.stk.mrkt.ojhastkmrktcompanydetailservice.model;

import java.util.List;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.response.SuccessResponse;

import lombok.Data;

@Data
public class StockDetailResponse extends SuccessResponse {

	private String id;
	private List<StockDetailEntity> stockDetails;
	private StockDetailEntity stockDetail;
	
}
