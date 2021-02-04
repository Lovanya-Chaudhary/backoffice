package com.rediron.platform.domain.report.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRSubreport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.engine.util.JRVisitorSupport;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class SubReportVisitor extends JRVisitorSupport {

	private final Logger logger = LoggerFactory.getLogger(SubReportVisitor.class);
	
	@Autowired
	private Errors errors;
	
	
	private final SubReportPath subReportPath;
	private JasperReport jasperReport;

	public SubReportVisitor(SubReportPath subReportPath, JasperReport jasperReport) {
		this.subReportPath = subReportPath;
		this.jasperReport = jasperReport;
	}

	@Override
	public void visitSubreport(JRSubreport subreport) {
		SubReportInfo subReportInfo = new SubReportInfo(subreport);
		compileAndSaveSubReport(subReportInfo);
	}

	private void compileAndSaveSubReport(SubReportInfo subReportInfo) {
		try {

			jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(getClass().getClassLoader()
					.getResourceAsStream(subReportPath.getSubReportJRXmlPath() + subReportInfo.getFileName())));

			// save sub report compiled files so that these can included by main jasper
			// report
			JRSaver.saveObject(jasperReport,
					subReportPath.getSubReportJasperPath() + subReportInfo.getFileName().replace(".jrxml", ".jasper"));

		} catch (JRException e) {
			logger.error("Could not compile subreport " + subReportInfo.getFileName() + "'.", e);
			throw new ServiceException(errors.getErrors().get("Illegal_State").formatMessage(subReportInfo.getFileName()));
		}
	}

}