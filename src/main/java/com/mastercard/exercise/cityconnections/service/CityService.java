package com.mastercard.exercise.cityconnections.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.mastercard.exercise.cityconnections.datastructure.CityGraph;
import com.mastercard.exercise.cityconnections.datastructure.search.specification.Finder;

@RestController
@RequestScope
public class CityService {
	
	@Autowired
	private CityGraph cityGraph;
	
	@Autowired
	private Finder<ResponseEntity<String>, String, String> pathFinder;

	
	@RequestMapping(value = "/connected", method = RequestMethod.GET)																																																																																																																																								
	public ResponseEntity<String> isConnected(@RequestParam(value="origin") String origin, @RequestParam(value="destination") String destination) {
		return pathFinder.find(origin, destination);
		
	}
}
