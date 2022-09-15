package com.rcs.restoreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rcs.restoreview.model.Review;
import com.rcs.restoreview.model.ReviewStatus;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findReviewsByRestaurantIdAndStatus(Long restaurantId, ReviewStatus reviewStatus);

	List<Review> findReviewsByStatus(ReviewStatus reviewStatus);
}
