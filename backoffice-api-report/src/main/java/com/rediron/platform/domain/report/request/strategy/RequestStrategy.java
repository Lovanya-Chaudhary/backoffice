package com.rediron.platform.domain.report.request.strategy;

import java.util.Map;

public interface RequestStrategy {

	void setParams(Map<String, Object> params);

	Map<String, Object> getParams();
}