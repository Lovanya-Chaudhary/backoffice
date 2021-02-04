package com.rediron.platform.domain.report.request.strategy.impl;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.rediron.platform.domain.report.constant.ReportConstants;
import com.rediron.platform.domain.report.constant.ReportName;
import com.rediron.platform.domain.report.request.strategy.ReportTypeStrategy;

@Component
public class CsvReportType implements ReportTypeStrategy {

	@Override
	public void setHeaders(ReportName reportName, HttpServletResponse response) {
		response.setHeader("Content-Disposition",
				"attachment; filename=" + reportName.value() + "_" + new Date().toString() + ".pdf");
		response.setHeader("Content-Type", ReportConstants.PDF_CONTENT_TYPE);
	}

}
