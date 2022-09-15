package com.rcs.restoreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.rcs.restoreview.model.Restaurant;
import com.rcs.restoreview.model.Review;
import com.rcs.restoreview.model.ReviewStatus;
import com.rcs.restoreview.model.User;
import com.rcs.restoreview.repository.RestaurantRepository;
import com.rcs.restoreview.repository.ReviewRepository;
import com.rcs.restoreview.repository.UserRepository;

import java.util.Optional;

@RequestMapping("/reviews")
@RestController
public class ReviewController {
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;

	@GetMapping
	public Iterable<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void addUserReview(@RequestBody Review review) {
		validateUserReview(review);

		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(review.getRestaurantId());
		if (optionalRestaurant.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		review.setStatus(ReviewStatus.PENDING);
		reviewRepository.save(review);
	}

	private void validateUserReview(Review review) {
		if (ObjectUtils.isEmpty(review.getSubmittedBy())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		if (ObjectUtils.isEmpty(review.getRestaurantId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		if (ObjectUtils.isEmpty(review.getPeanutScore()) && ObjectUtils.isEmpty(review.getDairyScore())
				&& ObjectUtils.isEmpty(review.getEggScore())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		Optional<User> optionalUser = userRepository.findUserByDisplayName(review.getSubmittedBy());
		if (optionalUser.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
