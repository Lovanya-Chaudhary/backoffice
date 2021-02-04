package com.rediron.platform.domain.report.strategy;

public class SubReportPath {

	/**
	 * Location of sub report jrxml files
	 */
	private String subReportJRXmlPath;

	/**
	 * Location of the folder to be provided for .jasper files(sub-report jrxml
	 * files compile as .jasper) and this location must be under project base
	 * directory and this folder must be included in maven build/packaging process
	 */
	private String subReportJasperPath;

	public SubReportPath(String subReportJRXmlPath, String subReportJasperPath) {
		this.subReportJRXmlPath = subReportJRXmlPath;
		this.subReportJasperPath = subReportJasperPath;
	}

	public String getSubReportJRXmlPath() {
		return subReportJRXmlPath;
	}

	public void setSubReportJRXmlPath(String subReportJRXmlPath) {
		this.subReportJRXmlPath = subReportJRXmlPath;
	}

	public String getSubReportJasperPath() {
		return subReportJasperPath;
	}

	public void setSubReportJasperPath(String subReportJasperPath) {
		this.subReportJasperPath = subReportJasperPath;
	}

}
