package com.stk.mrkt.ojhastkmrktcompanydetailservice.model;

import lombok.Data;

@Data
public class StockDetailRequest {

	private Long id;
	private Long companyCode;
	private Double stockPrice;

}
