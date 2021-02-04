package com.rediron.platform.domain.report.strategy.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rediron.platform.domain.report.strategy.ReportStrategy;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Component
public class CashOnHandReportReportStrategy implements ReportStrategy {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CashOnHandReportReportStrategy.class);
	
	@Value(value = "${report.cashOnHandReportPath}")
	private String cashOnHandReportPath;

	@Override
	public JasperDesign loadJasperDesign() throws JRException {
		LOGGER.info("=== Loading Jasper Design ===");
		JasperDesign jasperDesign = JRXmlLoader
				.load(getClass().getClassLoader().getResourceAsStream(cashOnHandReportPath));
		return jasperDesign;
	}

}
