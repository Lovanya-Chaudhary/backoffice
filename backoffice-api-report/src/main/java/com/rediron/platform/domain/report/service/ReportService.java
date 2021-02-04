package com.rediron.platform.domain.report.service;

import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.report.common.CustomReport;
import com.rediron.platform.domain.report.common.GenericReport;
import com.rediron.platform.domain.report.constant.ExportType;
import com.rediron.platform.domain.report.constant.ReportName;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 * Convenience report service wrapping a cache of standard report templates (one
 * for each entity class). Also allows report generation based on a custom
 * column subset.
 *
 *
 *
 */
@Service
public class ReportService {
	private static Logger LOGGER = LoggerFactory.getLogger(ReportService.class);
	@Autowired
	private CustomReport customReport;

	private final Map<Class, GenericReport> standardReports = new HashMap<>();

	/**
	 * Standard report generation.
	 *
	 * @param              <T> The type of entity represented in the report
	 * @param type         class type of the entity
	 * @param rows         a collection of entity beans
	 * @param outputStream stream to write the report to
	 * @param exportType   XLS or PDF
	 * @param title        the title to be written in the report
	 * @throws JRException
	 */
	public <T> void runReport(Class<T> type, Collection<T> rows, OutputStream outputStream, ExportType exportType,
			String title) throws JRException {
		GenericReport<T> report = standardReports.get(type);
		if (report == null) {
			report = new GenericReport<>(type);
			standardReports.put(type, report);
		}
		Exporter exporter;
		switch (exportType) {
		case XLS:
			exporter = new JRXlsExporter();
			break;
		case PDF:
		default:
			exporter = new JRPdfExporter();
			break;
		}
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		Map<String, Object> params = new HashMap<>();
		params.put("ReportTitle", title);
		report.runReport(rows, params, exporter);
	}

	/**
	 * Report generation based on custom subset of columns.
	 *
	 * @param              <T> The type of entity represented in the report
	 * @param type         class type of the entity
	 * @param rows         a collection of entity beans
	 * @param outputStream stream to write the report to
	 * @param exportType   XLS or PDF
	 * @param title        the title to be written in the report
	 * @param columns      subset of columns to be shown in the report
	 * @throws JRException
	 */
	public <T> void runReport(Class<T> type, Collection<T> rows, OutputStream outputStream, ExportType exportType,
			String title, Set<String> columns) throws JRException {
		GenericReport<T> report = new GenericReport<>(type, columns);
		Exporter exporter;
		switch (exportType) {
		case XLS:
			exporter = new JRXlsExporter();
			break;
		case PDF:
		default:
			exporter = new JRPdfExporter();
			break;
		}
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		Map<String, Object> params = new HashMap<>();
		params.put("ReportTitle", title);
		report.runReport(rows, params, exporter);
	}

	public <T> void runReport(ReportName reportName, Map<String, Object> params, OutputStream outputStream,
			ExportType exportType) throws JRException, SQLException {
		Exporter exporter;
		switch (exportType) {
		case XLS:
			exporter = new JRXlsExporter();
			break;
		case PDF:
			exporter = new JRPdfExporter();
			break;
		case CSV:
			exporter = new JRCsvExporter();
			break;
		case HTML:
			exporter = new HtmlExporter();
			break;
		default:
			exporter = new JRPdfExporter();
			break;
		}
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		customReport.runReport(reportName, params, exporter);
	}
}
