package com.mastercard.exercise.cityconnections.datastructure;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CityGraph {

	private LinkedList<LinkedList<String>> listOfOrigins;
	
	public void createCityGraph(String[] cityPairs) {		
		Set<String> citySet = getDistinctCitiesCount(cityPairs);
		initializeOrigins(citySet);
		populateCityGraph(cityPairs);		
	}
	
	private Set<String> getDistinctCitiesCount(String[] cityPairs) {		
		Set<String> citySet = new HashSet<String>();
		for(String cityPair : cityPairs) {
			String[] originDestinationPair = cityPair.split(",");
			for (String city : originDestinationPair) {
				if(city != null && !city.isEmpty()) {
					citySet.add(city.trim().toUpperCase());
				}
			}			
		}
		return citySet;		
	}
	
	private void initializeOrigins(Set<String> citySet) {		
		this.listOfOrigins = new LinkedList<LinkedList<String>>();
		int count =0;
		for(String city : citySet) {
			LinkedList<String> linkedList = new LinkedList<>();
			linkedList.addFirst(city);
			listOfOrigins.add(linkedList);
		}					
	}
	
	private void populateCityGraph(String[] cityPairs) {		
		for(String cityPair : cityPairs) {
			String[] originDestinationPair = cityPair.split(",");
				if (originDestinationPair.length == 2) {
					String origin = (originDestinationPair[0] != null && !originDestinationPair[0].trim().isEmpty() ) ? originDestinationPair[0].trim() : null;
					String destination = (originDestinationPair[1] != null && !originDestinationPair[1].trim().isEmpty()) ? originDestinationPair[1].trim() : null;
					if(origin != null && destination != null) {
						addCityToTheListOfConnectedCities(origin.toUpperCase(), destination.toUpperCase());
						addCityToTheListOfConnectedCities(destination.toUpperCase(), origin.toUpperCase());
					}
			}
		}		
	}
	
	private int findOriginIndex(String city){		
		int indexOfOrigin = -1;
		for (int i=0; i<listOfOrigins.size(); i++) {
			LinkedList<String> list = listOfOrigins.get(i);
			if (city.equalsIgnoreCase(list.getFirst())) {
				indexOfOrigin = i;
				break;
			}
		}
		return indexOfOrigin;
	}
	
	private void addCityToTheListOfConnectedCities(String origin, String destination) {
		LinkedList<String> listAssociatedWithParticularOrigin = getCitiesAssociatedWithParticularOrigin(origin);
	    listAssociatedWithParticularOrigin.add(destination.trim());
	}
	
	private LinkedList<String> getCitiesAssociatedWithParticularOrigin(String origin){
		int indexOfOrigin = findOriginIndex(origin);
		LinkedList<String> listAssociatedWithParticularOrigin = null;
		if (indexOfOrigin != -1) {
			listAssociatedWithParticularOrigin = listOfOrigins.get(indexOfOrigin);
		}
		return listAssociatedWithParticularOrigin;
	}
	
	public LinkedList<String> findNeighbouringCities(String origin) {
		LinkedList<String> listAssociatedWithParticularOrigin = getCitiesAssociatedWithParticularOrigin(origin);
		return listAssociatedWithParticularOrigin;
	}
	
	public boolean validateCity (String city) {
		boolean isValid = false;
		for(LinkedList<String> list : listOfOrigins) {
			if(city.equalsIgnoreCase(list.getFirst().toString())){
				isValid = true;
				break;
			}
		}
		return isValid;
	}

	
}
