package com.rediron.platform.domain.report.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.report.request.strategy.impl.CashManagementTransactionsRequest;
import com.rediron.platform.util.validator.ValidationStrategy;

@Component
public class CashManagementTransactionsRequestValidationStrategy implements ValidationStrategy<CashManagementTransactionsRequest> {

	private static Logger logger = LoggerFactory.getLogger(CashManagementTransactionsRequestValidationStrategy.class);
	
	@Override
	public void validate(CashManagementTransactionsRequest request) throws ServiceException {
		Map<String, Object> params = request.getParams();
		params.put("p_date", getFormattedDate((String) params.get("p_date")));
		params.put("p_print_zero_amts", "Y");
		logger.info("Parameters passed are :");
		params.forEach((k, v) -> {
			logger.info("Parameter : " + k + ", Value: " + v);
	    });
	}

	// put it in lib module
	private Date getFormattedDate(String dateString) {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		logger.info("Date param passed : "+date);
		return date;
	}
	
	

}
