package com.mastercard.exercise.cityconnections.datastructure.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.mastercard.exercise.cityconnections.constant.Constants;
import com.mastercard.exercise.cityconnections.constant.ErrorConstants;
import com.mastercard.exercise.cityconnections.datastructure.CityGraph;
import com.mastercard.exercise.cityconnections.datastructure.search.specification.Finder;
import com.mastercard.exercise.cityconnections.exceptions.NoCityFoundException;

@Component
@Scope("prototype")
public class PathFinder implements Finder<ResponseEntity<String>, String, String>{

	@Autowired
	NoCityFoundException noCityFoundExcpetion;
	
	@Autowired
	private CityGraph cityGraph;

	@Override
	public ResponseEntity<String> find(String origin, String destination) {

		ResponseEntity<String> responseEntity = areNotValidCities(origin, destination);
		if (responseEntity!= null) {
			return responseEntity;
		}
		String response = Constants.NO;
		origin = origin.trim().toUpperCase();
		destination = destination.trim().toUpperCase();
		Queue<String> queue = new LinkedList<>();	
		
		queue.add(origin);
		
		List<String> visitedCityList = new ArrayList<>();
		visitedCityList.add(origin);

		while(!queue.isEmpty()) {
			String city = queue.remove();
			LinkedList<String> neighbouringCities = cityGraph.findNeighbouringCities(city);
			if (neighbouringCities.contains(destination)) {
				response = Constants.YES;
				break;
			}
			addCitiesToQueue(neighbouringCities, queue, visitedCityList);			
		}		
		return new ResponseEntity<>(response, HttpStatus.valueOf(Constants.SUCCESS));
	}
	
	private void addCitiesToQueue(LinkedList<String> neighbouringCities, Queue<String> queue, List<String> visitedCityList) {
		for(String city : neighbouringCities) {
			if (!checkIfCityisVisited(city, visitedCityList)){
				queue.add(city);
			}	
			addCitiesToMarkedList(city, visitedCityList);
		}
	}
	
	private void addCitiesToMarkedList(String city, List<String> visitedCityList) {
		visitedCityList.add(city);
	}
	
	private boolean checkIfCityisVisited(String city, List<String> visitedCityList) {
		return visitedCityList.contains(city);
	}
	
	private ResponseEntity<String> areNotValidCities(String origin, String destination) {	
		String errorKey = null;
		if (areCitiesNullorEmtpy(origin, destination)) {
			errorKey = ErrorConstants.ORIGIN_DESTINATION_EMPTY;
		}else if(!areAllowedCities(origin, destination)) {
			errorKey = ErrorConstants.ORIGIN_DESTINATION_PAIR_NOT_FOUND;
		}
		if (errorKey != null) {
			return createResponse(errorKey);
		}else {
			return null;
		}
	}
	
	private boolean areCitiesNullorEmtpy(String origin, String destination) {
		boolean isNull = false;
		if (origin == null || destination == null || origin.trim().isEmpty() || destination.trim().isEmpty()){
			isNull = true;
		}
		return isNull;
	}
	
	private boolean areAllowedCities(String origin, String destination) {
		boolean isValidOrigin = cityGraph.validateCity(origin.trim().toUpperCase());
		boolean isValidDestination = cityGraph.validateCity(destination.trim().toUpperCase());
		return isValidOrigin && isValidDestination;
	}
	
	private ResponseEntity<String> createResponse(String errorKey) {
		return noCityFoundExcpetion.createErrorMessage(errorKey);
		
	}
	
	
	
}
