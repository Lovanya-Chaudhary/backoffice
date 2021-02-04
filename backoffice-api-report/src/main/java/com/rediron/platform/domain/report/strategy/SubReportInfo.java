package com.rediron.platform.domain.report.strategy;

import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JRSubreport;

public class SubReportInfo {

	private String fileName;

	public SubReportInfo(JRSubreport subreport) {
		fileName = extractFilename(subreport);
	}

	public String getFileName() {
		return fileName;
	}

	private String extractFilename(JRSubreport subreport) {
		String expression = subreport.getExpression().getText();

		StringTokenizer st = new StringTokenizer(expression, "\"/");

		while (st.hasMoreTokens()) {
			fileName = st.nextToken();
		}

		return fileName.replace(".jasper", ".jrxml");
	}

}