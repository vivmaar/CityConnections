package com.mastercard.exercise.cityconnections.datastructure;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import com.mastercard.exercise.cityconnections.setup.GraphSetup;


@RunWith(SpringRunner.class)
public class CityGraphTest{
	
    @ClassRule
    public static final GraphSetup graphSetup = new GraphSetup();
	
	@Test
	public void testFindNeighbouringCities() {
		LinkedList<String> neighbouringList = graphSetup.cityGraph.findNeighbouringCities("NEWARK");
		String[] neighbouringCities = neighbouringList.toArray(new String[neighbouringList.size()]);
		String[] expectedNeighbouringCities = {"NEWARK", "PHILADELPHIA", "BOSTON"};
		assertArrayEquals(expectedNeighbouringCities, neighbouringCities);
	}
	
	@Test
	public void  testValidateCity(){
		 assertTrue(graphSetup.cityGraph.validateCity("NEW YORK"));
		 assertFalse(graphSetup.cityGraph.validateCity("NASHVILLE"));
	}


}
