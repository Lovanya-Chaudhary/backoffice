package com.rediron.platform.util.validator;

import java.beans.Introspector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.rediron.platform.core.Errors.ErrorInfo;
import com.rediron.platform.core.ServiceException;

@Component
public class ValidationStrategyFactory {
	@Autowired
	public ApplicationContext context;

	public <V extends ValidationStrategy<?>, T> V getStrategy(Class<T> requestClazz) {

		String requestName = requestClazz.getSimpleName();
		String validationStrategy = requestName + "ValidationStrategy";
		return (V) context.getBean(Introspector.decapitalize(validationStrategy));
	}

	public <T> void validate(T request) {
		try {
			Class clazz = Class.forName(request.getClass().getName());
			ValidationStrategy<T> strategy = getStrategy(clazz);
			strategy.validate(request);

		} catch (ClassNotFoundException e) {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorCode("GENERIC_ERROR");
			errorInfo.setStatus(500);
			errorInfo.setCause(e);
			errorInfo.setMessage(e.getMessage());
			throw new ServiceException(errorInfo);

		}
	}

}