package com.rediron.platform.domain.report.request.strategy;

import javax.servlet.http.HttpServletResponse;

import com.rediron.platform.domain.report.constant.ReportName;

public interface ReportTypeStrategy {
	
	void setHeaders(ReportName reportName,HttpServletResponse response);
	
}
