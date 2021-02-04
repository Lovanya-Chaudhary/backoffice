package com.rediron.platform.domain.report.request.factory;

import java.beans.Introspector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.rediron.platform.domain.report.constant.ReportName;
import com.rediron.platform.domain.report.request.strategy.RequestStrategy;

@Component
public class RequestStrategyFactory {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RequestStrategyFactory.class);
	
	@Autowired
	public ApplicationContext context;

	public <V extends RequestStrategy> V getStrategy(ReportName reportName) {

		LOGGER.info("=== Getting strategy request for the report ===");
		String requestName = reportName.value();
		String requestStrategy = requestName + "Request";
		return (V) context.getBean(Introspector.decapitalize(requestStrategy));
	}

}