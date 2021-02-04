package com.rediron.platform.domain.report.validation;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.report.request.strategy.impl.PurchaseOrderRequest;
import com.rediron.platform.util.validator.ValidationStrategy;

@Component
public class PurchaseOrderRequestValidationStrategy implements ValidationStrategy<PurchaseOrderRequest> {

	private static Logger logger = LoggerFactory.getLogger(CashOnHandReportRequestValidationStrategy.class);
	
	@Override
	public void validate(PurchaseOrderRequest request) throws ServiceException {
		Map<String, Object> params = request.getParams();

		params.put("p_from_po_site_no", new BigDecimal((String) params.get("p_from_po_site_no")));
		params.forEach((k, v) -> {
			logger.info("Parameter : " + k + ", Value: " + v);
	    });
	}

}
