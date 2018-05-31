package com.mastercard.exercise.cityconnections.exceptions;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mastercard.exercise.cityconnections.constant.Constants;
import com.mastercard.exercise.cityconnections.constant.ErrorConstants;
import com.mastercard.exercise.cityconnections.reader.ErrorMessageReader;

@Component
public class CityConnnectionException extends AbstractCityConnectionException {
	
	Logger logger = LoggerFactory.getLogger(CityConnnectionException.class);
	
	@Autowired
	ErrorMessageReader errorMessageReader;
	
	public CityConnnectionException(){
		
	}

	public void throwCityException(Throwable cause) {
		String errorKey = ErrorConstants.STD_ERROR_MESSAGE;
		if (cause instanceof IOException) {
			if (cause.getMessage().contains(Constants.CITY_FILE_NAME)) {
				errorKey = ErrorConstants.CITIES_CONFIG_NOT_FOUND;
			}else {
				errorKey = ErrorConstants.ERROR_FILE_NOT_FOUND;
			}
		}else if (cause instanceof JSONException) {
			errorKey = ErrorConstants.INCORRECT_JSON;
		}else if(cause instanceof NoCityFoundException){
			errorKey = ErrorConstants.NO_CITIES_FOUND_IN_CONFIG;
		}
		handleException (errorKey, cause);
	}
	
	private void handleException(String errorKey, Throwable cause) {
		try {
			if (errorKey.equalsIgnoreCase(ErrorConstants.ERROR_FILE_NOT_FOUND)){
				logger.error(ErrorConstants.ERROR_FILE_NOT_FOUND+"    Cause:"+cause);
			}else {
			logger.error(errorMessageReader.getJSONErrorHttpStatus(errorKey).toString()+":"+errorMessageReader.getJSONErrorMessage(errorKey)
					+"    Cause:"+cause);
			}
		} catch (JSONException e) {
			logger.error(ErrorConstants.STD_ERROR_MESSAGE+"    Cause:"+cause);

		}
	}
	
	public ResponseEntity<String> createErrorMessage(String errorKey){
		try {
			return new ResponseEntity<>(errorMessageReader.getJSONErrorMessage(errorKey), HttpStatus.valueOf(errorMessageReader.getJSONErrorHttpStatus(errorKey)));
		} catch (JSONException e) {
			logger.error("" + e);
			return new ResponseEntity<>(ErrorConstants.STD_ERROR_MESSAGE, HttpStatus.valueOf(ErrorConstants.STD_ERROR_HTTP_STATUS));
		}
	}
}
