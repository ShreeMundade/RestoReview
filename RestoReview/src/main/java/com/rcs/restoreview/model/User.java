package com.rcs.restoreview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "User1")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String displayName;

	private String city;
	private String state;
	private String zipCode;

	private Boolean peanutWatch;
	private Boolean dairyWatch;
	private Boolean eggWatch;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public Boolean getPeanutWatch() {
		return peanutWatch;
	}

	public void setPeanutWatch(Boolean peanutWatch) {
		this.peanutWatch = peanutWatch;
	}

	public Boolean getDairyWatch() {
		return dairyWatch;
	}

	public void setDairyWatch(Boolean dairyWatch) {
		this.dairyWatch = dairyWatch;
	}

	public Boolean getEggWatch() {
		return eggWatch;
	}

	public void setEggWatch(Boolean eggWatch) {
		this.eggWatch = eggWatch;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", displayName=" + displayName + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + ", peanutWatch=" + peanutWatch + ", dairyWatch=" + dairyWatch + ", eggWatch="
				+ eggWatch + "]";
	}

	public User(Long userId, String displayName, String city, String state, String zipCode, Boolean peanutWatch,
			Boolean dairyWatch, Boolean eggWatch) {
		super();
		this.userId = userId;
		this.displayName = displayName;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.peanutWatch = peanutWatch;
		this.dairyWatch = dairyWatch;
		this.eggWatch = eggWatch;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}