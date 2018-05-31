package com.mastercard.exercise.cityconnections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mastercard.exercise.cityconnections.publisher.CityGraphPublisher;
import com.mastercard.exercise.cityconnections.specification.ConfigurationObjectPublisher;

@SpringBootApplication
public class CityConnectionsApplication {

	public static void main(String[] args) {		
		ConfigurableApplicationContext applicationContext = SpringApplication.run(CityConnectionsApplication.class, args);
		ConfigurationObjectPublisher cityGraphPublisher = (CityGraphPublisher)applicationContext.getBean("cityGraphPublisher");
		cityGraphPublisher.publish();		
	}
}
