package com.stk.mrkt.ojhastkmrktcompanydetailservice.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CompanyDetailsUtil {

	public HttpHeaders getHttpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(acceptableMediaTypes);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}
	
	public String getResponseId() {
		return String.valueOf(System.currentTimeMillis());
	}
}
