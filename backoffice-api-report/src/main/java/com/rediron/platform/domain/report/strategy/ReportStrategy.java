package com.rediron.platform.domain.report.strategy;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * 
 * @author
 *
 * 		This will be used if there are no sub-reports. See this also -
 *         {@link SubReportStrategy}
 */
public interface ReportStrategy {
	JasperDesign loadJasperDesign() throws JRException;

}