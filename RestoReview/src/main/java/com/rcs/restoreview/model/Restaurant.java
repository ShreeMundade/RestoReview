package com.rcs.restoreview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Restaurant1")
public class Restaurant {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long  restaurantId;

    private String name;

    private String line1;
    private String city;
    private String state;
    private String zipCode;

    private String phoneNumber;
    private String website;

    private String overallScore;
    private String peanutScore;
    private String dairyScore;
    private String eggScore;
	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", line1=" + line1 + ", city=" + city
				+ ", state=" + state + ", zipCode=" + zipCode + ", phoneNumber=" + phoneNumber + ", website=" + website
				+ ", overallScore=" + overallScore + ", peanutScore=" + peanutScore + ", dairyScore=" + dairyScore
				+ ", eggScore=" + eggScore + "]";
	}
	public Restaurant(Long restaurantId, String name, String line1, String city, String state, String zipCode,
			String phoneNumber, String website, String overallScore, String peanutScore, String dairyScore,
			String eggScore) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.line1 = line1;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.website = website;
		this.overallScore = overallScore;
		this.peanutScore = peanutScore;
		this.dairyScore = dairyScore;
		this.eggScore = eggScore;
	}
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getOverallScore() {
		return overallScore;
	}
	public void setOverallScore(String overallScore) {
		this.overallScore = overallScore;
	}
	public String getPeanutScore() {
		return peanutScore;
	}
	public void setPeanutScore(String peanutScore) {
		this.peanutScore = peanutScore;
	}
	public String getDairyScore() {
		return dairyScore;
	}
	public void setDairyScore(String dairyScore) {
		this.dairyScore = dairyScore;
	}
	public String getEggScore() {
		return eggScore;
	}
	public void setEggScore(String eggScore) {
		this.eggScore = eggScore;
	}
	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    
}
	