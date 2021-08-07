package com.stk.mrkt.ojhastkmrktcompanydetailservice.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "COMPANY_PROFILE")
public class CompanyDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_CDE")
	private Long companyCode;
	
	@Column(name = "C_NAME")
	private String companyName;
	
	@Column(name = "C_CEO")
	private String companyCEO;
	
	@Column(name = "C_TRN_OVR")
	private Double companyTurnover;
	
	@Column(name = "C_WBSTE")
	private String companyWebsite;
	
	@Column(name = "C_XCHNGE")
	private String stockExchangeList;

	@Column(name = "CRT_TMS")
	private Date creationDate;
	
	@Column(name = "UPDT_TMS")
	private Date updateDate;
	
	public Long getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(Long companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCEO() {
		return companyCEO;
	}

	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}

	public Double getCompanyTurnover() {
		return companyTurnover;
	}

	public void setCompanyTurnover(Double companyTurnover) {
		this.companyTurnover = companyTurnover;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getStockExchangeList() {
		return stockExchangeList;
	}

	public void setStockExchangeList(String stockExchangeList) {
		this.stockExchangeList = stockExchangeList;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
