package com.rediron.platform.core;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 367442861388121258L;

	private Errors.ErrorInfo errorInfo;

	public ServiceException(Errors.ErrorInfo errorInfo) {
		super(errorInfo.getMessage(), errorInfo.getCause());
		this.errorInfo = errorInfo;
	}

	public Errors.ErrorInfo getErrorInfo() {
		return errorInfo;
	}
}
