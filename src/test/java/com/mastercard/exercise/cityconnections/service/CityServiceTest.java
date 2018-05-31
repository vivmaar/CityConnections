package com.mastercard.exercise.cityconnections.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.mastercard.exercise.cityconnections.setup.GraphSetup;

@RunWith(SpringRunner.class)
@WebMvcTest(CityService.class)
public class CityServiceTest {
	
    @Autowired
    private MockMvc mockMvc;
    
       
    @ClassRule
    public static final GraphSetup graphSetup = new GraphSetup();

    
    @MockBean
    private CityService cityService;
     
   
    @Test
    public void testIsConnected() throws Exception {
    	 String origin = "Boston";
    	 String destination = "New York";
    	 ResponseEntity<String> response = new ResponseEntity<String>("yes", HttpStatus.OK);
    	 given(cityService.isConnected(origin, destination)).willReturn(response);
    	 mockMvc.perform(get("/connected?origin=Boston&destination=New York")) 
    	 .andExpect(content().contentType("text/plain;charset=UTF-8"))
    	 .andExpect(status().isOk())
    	 .andExpect(content().string(response.getBody()));
    }
    
    @Test
    public void testIsConnectedForCitiesNotConnected() throws Exception {
    	 String origin = "Boston";
    	 String destination = "Trenton";
    	 ResponseEntity<String> response = new ResponseEntity<String>("no", HttpStatus.OK);
    	 given(cityService.isConnected(origin, destination)).willReturn(response);
    	 mockMvc.perform(get("/connected?origin=Boston&destination=Trenton")) 
    	 .andExpect(content().contentType("text/plain;charset=UTF-8"))
    	 .andExpect(status().isOk())
    	 .andExpect(content().string(response.getBody()));
     }
    
    @Test
    public void testIsConnectedForCitiesNotPresent() throws Exception {
    	 String origin = "Boston";
    	 String destination = "Nashville";
    	 ResponseEntity<String> response = new ResponseEntity<String>("The origin or desination or both pair cannot be found.", HttpStatus.NOT_FOUND);
    	 given(cityService.isConnected(origin, destination)).willReturn(response);
    	 mockMvc.perform(get("/connected?origin=Boston&destination=Nashville")) 
    	 .andExpect(content().contentType("text/plain;charset=UTF-8"))
    	 .andExpect(status().isNotFound())
    	 .andExpect(content().string(response.getBody()));
     }
    
    @Test
    public void testIsConnectedForEmptyCities() throws Exception {
    	 String origin = "Boston";
    	 String destination = "";
    	 ResponseEntity<String> response = new ResponseEntity<String>("One or both of the Origin and Destination are not present in the request..", HttpStatus.UNPROCESSABLE_ENTITY);
    	 given(cityService.isConnected(origin, destination)).willReturn(response);
    	 mockMvc.perform(get("/connected?origin=Boston&destination=")) 
    	 .andExpect(content().contentType("text/plain;charset=UTF-8"))
    	 .andExpect(status().isUnprocessableEntity())
    	 .andExpect(content().string(response.getBody()));
     }
    
 }
