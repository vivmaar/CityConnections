package com.mastercard.exercise.cityconnections.reader;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mastercard.exercise.cityconnections.configuration.CityConfigurationLoader;
import com.mastercard.exercise.cityconnections.constant.Constants;
import com.mastercard.exercise.cityconnections.exceptions.CityConnnectionException;

@Component
public class CityConfigurationReader extends CityConfigurationLoader {

	@Autowired
	CityConnnectionException cityConnnectionException;
	
	
	public String[] readCityPairsFromCityTxtFile() throws IOException {
		String cityPairsTxt = loadConfiguration(Constants.CITY_FILE_NAME).trim();
		String[] cityPairs = {};
		if (cityPairsTxt != null && !cityPairsTxt.isEmpty()) {
			cityPairs = cityPairsTxt.split(Constants.CARRIAGE_RETURN);
		}
		return cityPairs;
	}

}
