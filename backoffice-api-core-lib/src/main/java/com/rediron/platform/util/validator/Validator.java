package com.rediron.platform.util.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {

	@Autowired
	private ValidationStrategyFactory strategyFactory;

	public <T> void validate(T request) {

		strategyFactory.validate(request);
	}

}
