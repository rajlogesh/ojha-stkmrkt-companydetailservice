package com.stk.mrkt.ojhastkmrktcompanydetailservice.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyServiceError;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyServiceErrors;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.util.CompanyDetailsUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CompanyServiceErrorXMLParser {

	private static Map<String, CompanyServiceError> map = new HashMap<>();
	
	@Autowired
	CompanyDetailsUtil util;

	@PostConstruct
	public void init() {
		final String method = "CompanyServiceErrorXMLParser::init";
		logger.debug("[{}] - Error xml parsing started", method);
		if (null == map || map.isEmpty()) {
			CompanyServiceErrors errors = (CompanyServiceErrors) util.convertFromXMLToObject();
			errorsMap(errors);
		}
		logger.debug("([{}] - Error xml parsing ended", method);
	}

	private void errorsMap(CompanyServiceErrors errors) {
		if (null != errors && null != errors.getCompanyServiceErrorList()
				&& !errors.getCompanyServiceErrorList().isEmpty()) {
			for (CompanyServiceError serviceError : errors.getCompanyServiceErrorList()) {
				map.put(serviceError.getErrorId(), serviceError);
			}
		}
	}

	public CompanyServiceError readErorMessage(String key) {
		final String method = "CompanyServiceErrorXMLParser::readyErrorMessage";
		logger.debug("[{}] - Reading the error details from map", method);
		return map.get(key);
	}
}
