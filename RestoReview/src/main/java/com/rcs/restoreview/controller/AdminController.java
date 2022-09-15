package com.rcs.restoreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.rcs.restoreview.model.AdminReviewAction;
import com.rcs.restoreview.model.Restaurant;
import com.rcs.restoreview.model.Review;
import com.rcs.restoreview.model.ReviewStatus;
import com.rcs.restoreview.repository.RestaurantRepository;
import com.rcs.restoreview.repository.ReviewRepository;
import com.rcs.restoreview.repository.UserRepository;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@RequestMapping("/admin")
@RestController
public class AdminController {
	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;

	private DecimalFormat decimalFormat = new DecimalFormat("0.00");

	@GetMapping("/reviews")
	public List<Review> getReviewsByStatus(@RequestParam String review_status) {
		ReviewStatus reviewStatus = ReviewStatus.PENDING;
		try {
			reviewStatus = ReviewStatus.valueOf(review_status.toUpperCase());
		} catch (IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		return reviewRepository.findReviewsByStatus(reviewStatus);
	}

	@PutMapping("/reviews/{reviewId}")
	public void performReviewAction(@PathVariable Long reviewId, @RequestBody AdminReviewAction adminReviewAction) {
		Optional<Review> optionalReview = reviewRepository.findById(reviewId);
		if (optionalReview.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		Review review = optionalReview.get();

		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(review.getRestaurantId());
		if (optionalRestaurant.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		if (adminReviewAction.getAccept()) {
			review.setStatus(ReviewStatus.ACCEPTED);
		} else {
			review.setStatus(ReviewStatus.REJECTED);
		}

		reviewRepository.save(review);
		updateRestaurantReviewScores(optionalRestaurant.get());
	}

	private void updateRestaurantReviewScores(Restaurant restaurant) {
		List<Review> reviews = reviewRepository.findReviewsByRestaurantIdAndStatus(restaurant.getRestaurantId(),
				ReviewStatus.ACCEPTED);
		if (reviews.size() == 0) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		int peanutSum = 0;
		int peanutCount = 0;
		int dairySum = 0;
		int dairyCount = 0;
		int eggSum = 0;
		int eggCount = 0;
		for (Review r : reviews) {
			if (!ObjectUtils.isEmpty(r.getPeanutScore())) {
				peanutSum += r.getPeanutScore();
				peanutCount++;
			}
			if (!ObjectUtils.isEmpty(r.getDairyScore())) {
				dairySum += r.getDairyScore();
				dairyCount++;
			}
			if (!ObjectUtils.isEmpty(r.getEggScore())) {
				eggSum += r.getEggScore();
				eggCount++;
			}
		}

		int totalCount = peanutCount + dairyCount + eggCount;
		int totalSum = peanutSum + dairySum + eggSum;

		float overallScore = (float) totalSum / totalCount;
		restaurant.setOverallScore(decimalFormat.format(overallScore));

		if (peanutCount > 0) {
			float peanutScore = (float) peanutSum / peanutCount;
			restaurant.setPeanutScore(decimalFormat.format(peanutScore));
		}

		if (dairyCount > 0) {
			float dairyScore = (float) dairySum / dairyCount;
			restaurant.setDairyScore(decimalFormat.format(dairyScore));
		}

		if (eggCount > 0) {
			float eggScore = (float) eggSum / eggCount;
			restaurant.setEggScore(decimalFormat.format(eggScore));
		}

		restaurantRepository.save(restaurant);
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
