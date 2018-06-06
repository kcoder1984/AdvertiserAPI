package com.media.advertiserapi.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media.advertiserapi.dao.AdvertiserRepository;
import com.media.advertiserapi.model.Advertiser;
import com.media.advertiserapi.service.AdvertiserService;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {
	
	@Autowired
	AdvertiserRepository advertiserRepository ; 

	public Advertiser createAdvertiser(Advertiser advertiser) {
		return advertiserRepository.save(advertiser);
	}

	public Optional<Advertiser> getAdvertiser(long id) {
		return advertiserRepository.findById(id);
	}
	
	public void updateAdvertiser(Advertiser advertiser) {
		advertiserRepository.save(advertiser);
	}

	public void deleteAdvertiser(long id) {
		advertiserRepository.deleteById(id);
	}

	public boolean validateAdvertiser(BigDecimal creditLimit, BigDecimal transactionAmount) {
		boolean isValid = false;
			BigDecimal remainingValue = creditLimit.subtract(transactionAmount);

			if (remainingValue.signum() >= 0) {
				isValid = true;
			}
		return isValid;
	}
	
}
