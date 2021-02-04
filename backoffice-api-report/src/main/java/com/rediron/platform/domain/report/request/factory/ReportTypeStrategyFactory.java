package com.rediron.platform.domain.report.request.factory;

import java.beans.Introspector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.rediron.platform.domain.report.constant.ExportType;
import com.rediron.platform.domain.report.request.strategy.ReportTypeStrategy;

@Component
public class ReportTypeStrategyFactory {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReportTypeStrategyFactory.class);
	
	@Autowired
	public ApplicationContext context;

	public <V extends ReportTypeStrategy> V getStrategy(ExportType reportType) {

		LOGGER.info("=== Getting ExportType ===");
		String name = reportType.value();
		String reportTypeStrategy = name + "ReportType";
		return (V) context.getBean(Introspector.decapitalize(reportTypeStrategy));
	}

}
