package com.rediron.platform.domain.report.strategy;

/**
 * 
 * @author
 * 
 * 		This will be used(Create implementation of this) only in case of sub
 *         reports else use {@link ReportStrategy}.
 *
 */
public interface SubReportStrategy extends ReportStrategy {

	SubReportPath getSubReportPath();

}