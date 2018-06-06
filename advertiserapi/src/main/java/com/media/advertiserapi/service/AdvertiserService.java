package com.media.advertiserapi.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.media.advertiserapi.model.Advertiser;


public interface AdvertiserService {

	public Advertiser createAdvertiser(Advertiser advertiser);
	
	public Optional<Advertiser> getAdvertiser(long id);
	
	public void updateAdvertiser(Advertiser advertiser);
	
	public void deleteAdvertiser(long id);
	
	public boolean validateAdvertiser(BigDecimal creditLimit,BigDecimal transactionAmount);
}
