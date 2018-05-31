package com.media.advertiserapi.model;

import java.math.BigDecimal;

public class Advertiser {

	
	String advertiserName;
	String contactFirstName;
	String contactLastName;
	BigDecimal creditLimit;
	/**
	 * @return the advertiserName
	 */
	public String getAdvertiserName() {
		return advertiserName;
	}
	/**
	 * @param advertiserName the advertiserName to set
	 */
	public void setAdvertiserName(String advertiserName) {
		this.advertiserName = advertiserName;
	}
	/**
	 * @return the contactFirstName
	 */
	public String getContactFirstName() {
		return contactFirstName;
	}
	/**
	 * @param contactFirstName the contactFirstName to set
	 */
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	/**
	 * @return the contactLastName
	 */
	public String getContactLastName() {
		return contactLastName;
	}
	/**
	 * @param contactLastName the contactLastName to set
	 */
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	/**
	 * @return the creditLimit
	 */
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}
	/**
	 * @param creditLimit the creditLimit to set
	 */
	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	
	
}
