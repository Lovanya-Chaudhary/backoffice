package com.rediron.platform.util.validator;

import com.rediron.platform.core.ServiceException;

public interface ValidationStrategy<T> {
	void validate(T request) throws ServiceException;
}