package com.rediron.platform.core;

import org.apache.logging.log4j.core.config.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rediron.platform.core.Errors.ErrorInfo;
import com.tomax.api.RNetRuntimeException;

import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private Errors errors;

	// changes starts here
	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorInfo errorInfo = errors.getErrors().get("ILLEGAL_ARGUMENT");
		errorInfo.setCause(ex);
		logger.error(errorInfo);
		logger.error(errorInfo.getMessage());

		logger.error(ex);
		logger.error(ex.getMessage());
		return buildResponseEntity(errorInfo);

	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentMismatch(MethodArgumentTypeMismatchException ex) {

		logger.debug("############################## Handling form validation error");
		ErrorInfo errorInfo = errors.getErrors().get("METHOD_ARGUMENT_MISMATCH");
		errorInfo.setCause(ex);
		logger.error(errorInfo.getMessage());
		logger.error(errorInfo);

		logger.error(ex);
		logger.error(ex.getMessage());
		return buildResponseEntity(errorInfo);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		logger.debug("############################## Handling form validation error");
		ErrorInfo errorInfo = errors.getErrors().get("METHOD_ARGUMENT_NOT_VALID");
		errorInfo.addValidationErrors(ex.getBindingResult().getFieldErrors());
		errorInfo.addValidationError(ex.getBindingResult().getGlobalErrors());
		errorInfo.setCause(ex);
		logger.error(errorInfo.getMessage());
		logger.error(errorInfo);

		logger.error(ex);
		logger.error(ex.getMessage());
		return buildResponseEntity(errorInfo);
	}

	@ExceptionHandler(ServiceException.class)
	protected ResponseEntity<Object> handleServiceException(ServiceException serviceException) {
		logger.error(serviceException);
		logger.error(serviceException.getMessage());
		return buildResponseEntity(serviceException.getErrorInfo());
	}

	/**
	 * Generic exception handler.
	 * 
	 * @param serviceException
	 * @return
	 */
	@ExceptionHandler(RNetRuntimeException.class)
	protected ResponseEntity<Object> handleRnetException(RNetRuntimeException exception) {
		ErrorInfo errorInfo = errors.getErrors().get("RNET_ERROR");
		errorInfo.setCause(exception);
		errorInfo.setErrorCode(String.valueOf(exception.getError().getErrorCode()));
		errorInfo.setMessage(exception.getMessage());
		errorInfo.setStatus(exception.getError().getHttpStatus());
//		errorInfo.setDebugMessage(exception.getMessage());
		logger.error(errorInfo);
		return buildResponseEntity(errorInfo);

	}

	/**
	 * Generic exception handler.
	 * 
	 * @param serviceException
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleGenericException(Exception exception) {
		ErrorInfo errorInfo = errors.getErrors().get("GENERIC_ERROR");
		errorInfo.setCause(exception);
		if (!(exception instanceof NullPointerException)) {
//			errorInfo.setDebugMessage(exception.getMessage());
			errorInfo.setMessage(exception.getMessage());
		}
		logger.error(errorInfo);
		return buildResponseEntity(errorInfo);
	}

	// Provide one way to build a ResponseEntity
	private ResponseEntity<Object> buildResponseEntity(Errors.ErrorInfo errorInfo) {
		return new ResponseEntity<>(errorInfo, HttpStatus.valueOf(errorInfo.getStatus()));
	}
}
