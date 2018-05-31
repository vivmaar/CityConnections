package com.mastercard.exercise.cityconnections.configuration;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.mastercard.exercise.cityconnections.specification.ConfigurationLoader;


@Component
public abstract class CityConfigurationLoader implements ConfigurationLoader<String, String>{

    @Override
	public String loadConfiguration(String path) throws IOException {    	
		String content = StreamUtils.copyToString( new ClassPathResource(path).getInputStream(), Charset.defaultCharset());
 		return content;		
	}
}
