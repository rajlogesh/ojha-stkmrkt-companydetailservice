package com.stk.mrkt.ojhastkmrktcompanydetailservice.model;

import java.util.Date;

import lombok.Data;

@Data
public class StockDetailEntity {

	private String id;
	private Long companyCode;
	private Double stockPrice;
	private Date stockUpdtTms;

}