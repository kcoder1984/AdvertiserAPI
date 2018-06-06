package com.media.advertiserapi.service.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.media.advertiserapi.Application;
import com.media.advertiserapi.model.Advertiser;
import com.media.advertiserapi.service.AdvertiserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class AdvertiserServiceImplTest {

	@Autowired
	AdvertiserService advertiserService;
	
	@Test
	public void testCreateAdvertiser() {
		
		Advertiser advertiser = new Advertiser();
		advertiser.setAdvertiserName("AdvertiserName");
		advertiser.setContactFirstName("ContactFirstName");
		advertiser.setContactLastName("ContactLastName");
		advertiser.setCreditLimit(BigDecimal.valueOf(1000));
		
		Advertiser createdAdvertiser = advertiserService.createAdvertiser(advertiser);
		assertNotNull(createdAdvertiser);
		assertEquals("Name not matching", advertiser.getAdvertiserName(), createdAdvertiser.getAdvertiserName());
		
	}

	@Test
	public void testGetAdvertiser() {
		
		Advertiser advertiser = new Advertiser();
		advertiser.setAdvertiserName("AdvertiserName");
		advertiser.setContactFirstName("ContactFirstName");
		advertiser.setContactLastName("ContactLastName");
		advertiser.setCreditLimit(BigDecimal.valueOf(1000));
		
		Advertiser createdAdvertiser = advertiserService.createAdvertiser(advertiser);
		assertNotNull(createdAdvertiser);
		
		Optional<Advertiser> getAdvertiser = advertiserService.getAdvertiser(createdAdvertiser.getId());
		assertTrue(getAdvertiser.isPresent());
		assertNotNull(getAdvertiser.get());
	}

	@Test
	public void testUpdateAdvertiser() {
		Advertiser advertiser = new Advertiser();
		advertiser.setAdvertiserName("AdvertiserName");
		advertiser.setContactFirstName("ContactFirstName");
		advertiser.setContactLastName("ContactLastName");
		advertiser.setCreditLimit(BigDecimal.valueOf(1000));
		
		Advertiser createdAdvertiser = advertiserService.createAdvertiser(advertiser);
		assertNotNull(createdAdvertiser);

		createdAdvertiser.setAdvertiserName("updatedName");
		advertiserService.updateAdvertiser(createdAdvertiser);
		Optional<Advertiser> updatedAdvertiser = advertiserService.getAdvertiser(createdAdvertiser.getId());
		assertTrue(updatedAdvertiser.isPresent());
		assertEquals("Name failed to Update", createdAdvertiser.getAdvertiserName(), updatedAdvertiser.get().getAdvertiserName());
		
	}

	@Test
	public void testDeleteAdvertiser() {
		Advertiser advertiser = new Advertiser();
		advertiser.setAdvertiserName("AdvertiserName");
		advertiser.setContactFirstName("ContactFirstName");
		advertiser.setContactLastName("ContactLastName");
		advertiser.setCreditLimit(BigDecimal.valueOf(1000));
		
		Advertiser createdAdvertiser = advertiserService.createAdvertiser(advertiser);
		assertNotNull(createdAdvertiser);

		advertiserService.deleteAdvertiser(createdAdvertiser.getId());
		
		Optional<Advertiser> deletedAdvertiser = advertiserService.getAdvertiser(createdAdvertiser.getId());
		
		assertFalse(deletedAdvertiser.isPresent());
		
	}

	@Test
	public void testValidateAdvertiser() {
		
		Advertiser advertiser = new Advertiser();
		advertiser.setAdvertiserName("AdvertiserName");
		advertiser.setContactFirstName("ContactFirstName");
		advertiser.setContactLastName("ContactLastName");
		advertiser.setCreditLimit(BigDecimal.valueOf(1000));
		
		Advertiser createdAdvertiser = advertiserService.createAdvertiser(advertiser);
		assertNotNull(createdAdvertiser);

		assertTrue("Credit limit is equal to required transaction.",advertiserService.validateAdvertiser(createdAdvertiser.getCreditLimit(), BigDecimal.valueOf(1000)));
		
		assertTrue("Credit limit is greater then required transaction.",advertiserService.validateAdvertiser(createdAdvertiser.getCreditLimit(), BigDecimal.valueOf(999)));
		
		assertFalse("Credit limit is less then required transaction.",advertiserService.validateAdvertiser(createdAdvertiser.getCreditLimit(), BigDecimal.valueOf(1001)));
	}

}
