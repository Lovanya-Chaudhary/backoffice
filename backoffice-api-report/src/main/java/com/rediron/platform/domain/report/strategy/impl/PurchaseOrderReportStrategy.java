package com.rediron.platform.domain.report.strategy.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rediron.platform.domain.report.strategy.SubReportPath;
import com.rediron.platform.domain.report.strategy.SubReportStrategy;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Component
public class PurchaseOrderReportStrategy implements SubReportStrategy {
	
	private static Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderReportStrategy.class);
	
	@Value(value = "${report.purchaseOrderReportDirPath}")
	private String JRXML_PATH;// = "reports/purchasing/purchase_order_report_files/";
	
	@Value(value = "${jasper.compiledReports}")
	private String SUB_REPORT_JASPER_PATH;//  = "compiled-reports/";

	@Override
	public JasperDesign loadJasperDesign() throws JRException {
		LOGGER.info("=== Loading Jasper Design ===");
		JasperDesign jasperDesign = JRXmlLoader
				.load(getClass().getClassLoader().getResourceAsStream(JRXML_PATH + "main.jrxml"));
		return jasperDesign;
	}

	@Override
	public SubReportPath getSubReportPath() {
		SubReportPath subReportPath = new SubReportPath(JRXML_PATH, SUB_REPORT_JASPER_PATH);
		return subReportPath;
	}

}
