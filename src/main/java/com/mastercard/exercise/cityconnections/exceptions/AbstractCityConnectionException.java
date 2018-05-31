package com.mastercard.exercise.cityconnections.exceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public abstract class AbstractCityConnectionException extends Throwable{
	private String httpStatus;
	private String errorMessage;
	private Throwable cause;
	
	public AbstractCityConnectionException() {
		super();
	}
	
	public AbstractCityConnectionException(String message) {
		super(message);
	}
	
	public AbstractCityConnectionException(String httpStatus, String errorMessage) {
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}
	
	public AbstractCityConnectionException(String httpStatus, String errorMessage, Throwable cause) {
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.cause = cause;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}
	
	public abstract void throwCityException(Throwable cause);
	
	
}
