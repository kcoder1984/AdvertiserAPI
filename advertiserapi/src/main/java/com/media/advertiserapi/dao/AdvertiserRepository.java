package com.media.advertiserapi.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.media.advertiserapi.model.Advertiser;

/**
 * Advertiser Repository
 */
public interface AdvertiserRepository extends PagingAndSortingRepository<Advertiser, Long>{
   
}
