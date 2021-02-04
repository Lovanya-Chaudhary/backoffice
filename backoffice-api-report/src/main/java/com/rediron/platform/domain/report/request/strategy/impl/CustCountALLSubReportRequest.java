package com.rediron.platform.domain.report.request.strategy.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.rediron.platform.domain.report.request.strategy.RequestStrategy;

@Component
public class CustCountALLSubReportRequest implements RequestStrategy {

	private Map<String, Object> params;

	@Override
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public Map<String, Object> getParams() {

		return this.params;
	}

}
