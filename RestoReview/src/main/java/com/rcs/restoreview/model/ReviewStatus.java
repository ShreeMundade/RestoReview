package com.rcs.restoreview.model;


import com.fasterxml.jackson.annotation.JsonCreator;



public enum ReviewStatus {
	PENDING, REJECTED, ACCEPTED;

	@JsonCreator
	public static ReviewStatus create(String value) {
		if (value == null) {
			return null;
		}
		for (ReviewStatus reviewStatus : values()) {
			if (value.equals(reviewStatus.toString())) {
				return reviewStatus;
			}
		}
		return null;
	}
}
