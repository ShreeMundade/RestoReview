package com.rcs.restoreview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Review1")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;

    private String submittedBy;
    private Long restaurantId;
    private String review;

    private Integer peanutScore;
    private Integer dairyScore;
    private Integer eggScore;

    
    @Enumerated(value = EnumType.STRING)
	@Column(name = "ReviewStatus")
    private ReviewStatus status;
    
    

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Integer getPeanutScore() {
		return peanutScore;
	}

	public void setPeanutScore(Integer peanutScore) {
		this.peanutScore = peanutScore;
	}

	public Integer getDairyScore() {
		return dairyScore;
	}

	public void setDairyScore(Integer dairyScore) {
		this.dairyScore = dairyScore;
	}

	public Integer getEggScore() {
		return eggScore;
	}

	public void setEggScore(Integer eggScore) {
		this.eggScore = eggScore;
	}

	public ReviewStatus getStatus() {
		return status;
	}

	public void setStatus(ReviewStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", submittedBy=" + submittedBy + ", restaurantId=" + restaurantId
				+ ", review=" + review + ", peanutScore=" + peanutScore + ", dairyScore=" + dairyScore + ", eggScore="
				+ eggScore + ", status=" + status + "]";
	}

	public Review(Long reviewId, String submittedBy, Long restaurantId, String review, Integer peanutScore,
			Integer dairyScore, Integer eggScore, ReviewStatus status) {
		super();
		this.reviewId = reviewId;
		this.submittedBy = submittedBy;
		this.restaurantId = restaurantId;
		this.review = review;
		this.peanutScore = peanutScore;
		this.dairyScore = dairyScore;
		this.eggScore = eggScore;
		this.status = status;
	}

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}