package com.mastercard.exercise.cityconnections.reader;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import com.mastercard.exercise.cityconnections.configuration.CityConfigurationLoader;
import com.mastercard.exercise.cityconnections.constant.Constants;
import com.mastercard.exercise.cityconnections.constant.ErrorConstants;

@Component
public class ErrorMessageReader extends CityConfigurationLoader {
	
	private JSONObject jsonErrorMessages;
	
	public void readErorMessages() throws JSONException, IOException{		
		String errorMessages = loadConfiguration(Constants.ERROR_FILE_NAME);
		jsonErrorMessages = new JSONObject(errorMessages);		
	}
	
	
	public String getJSONErrorMessage(String errorKey) throws JSONException {	
		return getErrorMessage(errorKey) != null ? getErrorMessage(errorKey).get(Constants.MESSAGE).toString() : ErrorConstants.STD_ERROR_MESSAGE;		
	}
	
	public String getJSONErrorHttpStatus(String errorKey) throws JSONException {		
		return getErrorMessage(errorKey) != null ? getErrorMessage(errorKey).get(Constants.HTTP_STATUS).toString() : ErrorConstants.STD_ERROR_HTTP_STATUS;		
	}
	
	private JSONObject getErrorMessage(String errorKey) throws JSONException {
		return jsonErrorMessages != null ? (JSONObject)jsonErrorMessages.getJSONObject(errorKey) : null;
	}
	
	
	
}
