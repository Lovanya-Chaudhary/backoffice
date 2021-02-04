package com.rediron.platform.domain.report.common;

import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.report.constant.ReportName;
import com.rediron.platform.domain.report.factory.ReportStrategyFactory;
import com.rediron.platform.domain.report.strategy.SubReportVisitor;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRElementsVisitor;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;

@Component
public class CustomReport {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CustomReport.class);

	@Autowired
	private ReportStrategyFactory reportingStrategyFactory;

	@Autowired
	private DataSource dataSource;
	
	@Value(value = "${jasper.imageURL}")
	private String imageURL;
	
	@Autowired
	private Errors errors;

	public void runReport(ReportName reportName, Map<String, Object> params, Exporter exporter)
			throws JRException, SQLException {
		JasperPrint jasperPrint = null;

		try {

			LOGGER.info("===== Compiling main report =====");
			// Compiling main report
			JasperReport jasperReport = JasperCompileManager
					.compileReport(reportingStrategyFactory.getJRXML(reportName.value()));

			// Compiling and saving sub reports if present

			if (reportingStrategyFactory.getSubReportPath(reportName.value()) != null) {
				LOGGER.info("===== Compiling sub report =====");
				SubReportVisitor subReportVisitor = new SubReportVisitor(
						reportingStrategyFactory.getSubReportPath(reportName.value()), jasperReport);
				JRElementsVisitor.visitReport(jasperReport, subReportVisitor);
				params.put("SubReportDirJasper",
						reportingStrategyFactory.getSubReportPath(reportName.value()).getSubReportJasperPath());
			}
			params.put("OnServer", false);
			params.put("ImageURL", imageURL);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServiceException(errors.getErrors().get("GENERIC_ERROR"));
		}

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.exportReport();
	}
}
