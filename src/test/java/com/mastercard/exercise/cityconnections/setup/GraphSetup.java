package com.mastercard.exercise.cityconnections.setup;

import org.junit.rules.ExternalResource;
import org.springframework.stereotype.Component;
import com.mastercard.exercise.cityconnections.datastructure.CityGraph;

@Component
public class GraphSetup  extends ExternalResource {
	
	public CityGraph cityGraph;

    protected void before() {
    	cityGraph = new CityGraph();
    	String[] cityPairs = {"Boston, New York", "Philadelphia, Newark", "Newark, Boston", "Trenton, Albany"};
    	cityGraph.createCityGraph(cityPairs);
    }
    
    protected void after() {

    }


}
