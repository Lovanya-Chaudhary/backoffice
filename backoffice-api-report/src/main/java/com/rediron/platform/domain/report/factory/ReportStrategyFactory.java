package com.rediron.platform.domain.report.factory;

import java.beans.Introspector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.rediron.platform.domain.report.strategy.ReportStrategy;
import com.rediron.platform.domain.report.strategy.SubReportPath;
import com.rediron.platform.domain.report.strategy.SubReportStrategy;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;

@Component
public class ReportStrategyFactory {
	private static Logger LOGGER = LoggerFactory.getLogger(ReportStrategyFactory.class);
	@Autowired
	public ApplicationContext context;

	public <R extends ReportStrategy> R getStrategy(String reportName) {// Report name should be
		LOGGER.info("=== Getting Report Strategy ===");
		String reportStrategy = reportName + "ReportStrategy";// report name should be suffixed with 'ReportStrategy'

		return (R) context.getBean(Introspector.decapitalize(reportStrategy));
	}

	public JasperDesign getJRXML(String reportName) throws JRException {
		LOGGER.info("=== Getting JRXML for ==="+reportName);
		ReportStrategy reportStrategy = getStrategy(reportName);
		return reportStrategy.loadJasperDesign();
	}

	public SubReportPath getSubReportPath(String reportName) {
		if (getStrategy(reportName) instanceof SubReportStrategy) {
			LOGGER.info("=== Getting sub report for ==="+reportName);
			return ((SubReportStrategy) getStrategy(reportName)).getSubReportPath();
		}
		return null;

	}

}