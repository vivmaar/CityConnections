package com.mastercard.exercise.cityconnections.reader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import com.mastercard.exercise.cityconnections.constant.Constants;

@RunWith(SpringRunner.class)
public class CityConfigurationReaderTest {
	
	CityConfigurationReader cityConfigurationReader = new CityConfigurationReader();

	@Test
	public void testLoadConfiguration() throws IOException {
		String content = cityConfigurationReader.loadConfiguration(Constants.CITY_FILE_NAME);
		assertNotNull(content);
	}
	
	@Test(expected = IOException.class)
	public void testLoadConfigurationForException() throws IOException {
		cityConfigurationReader.loadConfiguration("incorrectpath/city.txt");
	}
	
	@Test
	public void testReadCityPairsFromCityTxtFile() throws IOException {
		String[] cityPairs = cityConfigurationReader.readCityPairsFromCityTxtFile();
		String[] expectedCityPairs = {"Boston, New York","Philadelphia, Newark", "Newark, Boston", "Trenton, Albany"};
		assertArrayEquals(cityPairs, expectedCityPairs);
	}

}
