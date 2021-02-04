package com.rediron.platform.domain.report.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.Errors.ErrorInfo;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.report.constant.ExportType;
import com.rediron.platform.domain.report.constant.ReportName;
import com.rediron.platform.domain.report.request.factory.ReportTypeStrategyFactory;
import com.rediron.platform.domain.report.request.factory.RequestStrategyFactory;
import com.rediron.platform.domain.report.request.strategy.ReportTypeStrategy;
import com.rediron.platform.domain.report.request.strategy.RequestStrategy;
import com.rediron.platform.domain.report.service.ReportService;
import com.rediron.platform.util.validator.Validator;

import io.swagger.annotations.ApiParam;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/v1/reports")
public class ReportController {
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private ReportService reportingService;
	
	@Autowired
	private ReportTypeStrategyFactory reportTypeStrategyFactory;

	@Autowired
	private Errors errors;

	@Autowired
	private Validator validator;

	@Autowired
	private RequestStrategyFactory requestStrategyFactory;

	@RequestMapping(method = RequestMethod.GET, value = "/report")
//	@ApiImplicitParam(name = "params", value = "Parameters required to execute the report", required = true, dataType = "Map<ReportParam, Object>.class", paramType = "query")

	public void getReport(@RequestHeader(value = "udtoken") String userToken,
			@RequestParam(defaultValue = "", required = true, name = "report_name") ReportName reportName,
			@RequestParam(defaultValue = "", required = true, name = "export_type") ExportType exportType,
			@ApiParam(value = "Map of report elements", required = false, allowableValues = "COH - Group Id | Site Name, HMU - Employee Id", allowMultiple = true) @RequestParam(defaultValue = "", required = false) Map<String, Object> params,
			HttpServletResponse response) {
		logger.info("Entered service");
		try (OutputStream os = response.getOutputStream()) {
			ReportTypeStrategy reportTypeStrategy = reportTypeStrategyFactory.getStrategy(exportType);
			reportTypeStrategy.setHeaders(reportName, response);
			
			RequestStrategy requestStrategy = requestStrategyFactory.getStrategy(reportName);
			requestStrategy.setParams(params);
			validator.validate(requestStrategy);

			reportingService.runReport(reportName, params, os, exportType);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (JRException | SQLException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(errors.getErrors().get("GENERIC_ERROR"));
		}
	}
	// report changes ends

}
