package com.rediron.platform.domain.report.validation;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.report.request.strategy.impl.CashDrawerContentsRequest;
import com.rediron.platform.util.validator.ValidationStrategy;

@Component
public class CashDrawerContentsRequestValidationStrategy implements ValidationStrategy<CashDrawerContentsRequest> {

	private static Logger logger = LoggerFactory.getLogger(CashDrawerContentsRequestValidationStrategy.class);
	
	@Override
	public void validate(CashDrawerContentsRequest request) throws ServiceException {
		Map<String, Object> params = request.getParams();
		logger.info("Parameters passed are :");
		params.forEach((k, v) -> {
			logger.info("Parameter : " + k + ", Value: " + v);
	    });
	}

}
