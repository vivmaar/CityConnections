package com.mastercard.exercise.cityconnections.publisher;

import java.io.IOException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mastercard.exercise.cityconnections.datastructure.CityGraph;
import com.mastercard.exercise.cityconnections.exceptions.CityConnnectionException;
import com.mastercard.exercise.cityconnections.exceptions.NoCityFoundException;
import com.mastercard.exercise.cityconnections.reader.CityConfigurationReader;
import com.mastercard.exercise.cityconnections.reader.ErrorMessageReader;
import com.mastercard.exercise.cityconnections.specification.ConfigurationObjectPublisher;

@Component
public class CityGraphPublisher implements ConfigurationObjectPublisher{
	
	Logger logger = LoggerFactory.getLogger(CityGraphPublisher.class);

	@Autowired
	private CityGraph cityGraph;
	
	@Autowired
	private CityConfigurationReader cityConfigurationReader;
	
	@Autowired
	private ErrorMessageReader errorMessageReader;
	
	@Autowired
	CityConnnectionException cityConnnectionException;
		
	private void publishCityGraph() {		
		try {
			String[] cityPairs;
			errorMessageReader.readErorMessages();
			cityPairs = cityConfigurationReader.readCityPairsFromCityTxtFile();
			if (cityPairs.length > 0) {
				cityGraph.createCityGraph(cityPairs);
			}else {
				throw new NoCityFoundException();
			}
		} catch (IOException |JSONException|NoCityFoundException e) {
			cityConnnectionException.throwCityException(e);
		}
		
	}

	@Override
	public void publish() {
		publishCityGraph();		
	}
	
	
}
