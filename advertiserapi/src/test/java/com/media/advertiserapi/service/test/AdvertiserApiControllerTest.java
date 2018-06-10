package com.media.advertiserapi.service.test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.media.advertiserapi.Application;
import com.media.advertiserapi.model.Advertiser;
import com.media.advertiserapi.rest.AdvertiserApiController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class AdvertiserApiControllerTest {
	
	private static final String BASE_URL = "/api/advertiser/";

	@InjectMocks
	AdvertiserApiController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
	public void CRUD() throws Exception {
		Advertiser adv = mockAdvertisement("CRUD");
		byte[] advJson = toJson(adv);
		
		int id = 1;

		// CREATE
		mvc.perform(post(BASE_URL).content(advJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is((int) id)))
				.andExpect(jsonPath("$.advertiserName", is(adv.getAdvertiserName())))
				.andExpect(jsonPath("$.contactFirstName", is(adv.getContactFirstName())))
				.andExpect(jsonPath("$.contactLastName", is(adv.getContactLastName())))
				.andExpect(jsonPath("$.creditLimit", is(100.50)));
       adv.setId(id);

		// RETRIEVE
		mvc.perform(get(BASE_URL + id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is((int) 1)))
				.andExpect(jsonPath("$.advertiserName", is(adv.getAdvertiserName())))
				.andExpect(jsonPath("$.contactFirstName", is(adv.getContactFirstName())))
				.andExpect(jsonPath("$.contactLastName", is(adv.getContactLastName())))
				.andExpect(jsonPath("$.creditLimit", is(100.50)));

		//UPDATE
		adv.setAdvertiserName("updateName");
		assertEquals("Advertisement name should be updated.", "updateName", adv.getAdvertiserName());
		advJson = toJson(adv);
		
		mvc.perform(put(BASE_URL+id).content(advJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
		
				
		// RETRIEVE to check updated
		mvc.perform(get(BASE_URL + id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is((int) 1)))
				.andExpect(jsonPath("$.advertiserName", is(adv.getAdvertiserName())))
				.andExpect(jsonPath("$.contactFirstName", is(adv.getContactFirstName())))
				.andExpect(jsonPath("$.contactLastName", is(adv.getContactLastName())))
				.andExpect(jsonPath("$.creditLimit", is(100.50)));

		
		//Dataformat exception
		adv.setId(3);
    	advJson = toJson(adv);
		mvc.perform(put(BASE_URL+"1").content(advJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
		
		//Resource not found exception
		mvc.perform(get(BASE_URL + "3").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		
		
		// DELETE
		mvc.perform(delete(BASE_URL + id)).andExpect(status().isNoContent());

		// RETRIEVE should fail
		mvc.perform(get(BASE_URL + id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		
		
	}
    
    @Test
    public void checkTransaction() throws Exception {
    	Advertiser adv = mockAdvertisement("CRUD");
		byte[] advJson = toJson(adv);
    	int id = 2;

		// CREATE
		mvc.perform(post(BASE_URL).content(advJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is((int) id)))
				.andExpect(jsonPath("$.advertiserName", is(adv.getAdvertiserName())))
				.andExpect(jsonPath("$.contactFirstName", is(adv.getContactFirstName())))
				.andExpect(jsonPath("$.contactLastName", is(adv.getContactLastName())))
				.andExpect(jsonPath("$.creditLimit", is(100.50)));
       adv.setId(id);

    	
		// Successful transaction
		MvcResult result = mvc.perform(get(BASE_URL + id + "/20").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals("Transaction should pass.", "true", result.getResponse().getContentAsString());

		// Failed transaction
		result = mvc.perform(get(BASE_URL + id + "/120").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();
		assertEquals("Transaction should pass.", "false", result.getResponse().getContentAsString());
			
    }
    

    
	private Advertiser mockAdvertisement(String prefix) {
        Advertiser adv = new Advertiser();
        adv.setAdvertiserName(prefix+"_advname");
        adv.setContactFirstName(prefix+"_firstName");
        adv.setContactLastName(prefix+"_lastName");
        adv.setCreditLimit(BigDecimal.valueOf(100.50));
        return adv;
    }

	
	
    private byte[] toJson(Object obj) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(obj).getBytes();
    }
	
}
