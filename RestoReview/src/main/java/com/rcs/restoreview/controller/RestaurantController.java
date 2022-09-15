package com.rcs.restoreview.controller;

import java.util.Collections;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rcs.restoreview.model.Restaurant;
import com.rcs.restoreview.repository.RestaurantRepository;

@RequestMapping("/restaurants")
@RestController
public class RestaurantController {
	@Autowired
	private RestaurantRepository restaurantRepository;
	private Pattern zipCodePattern = Pattern.compile("\\d{5}");

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
		validateNewRestaurant(restaurant);

		return restaurantRepository.saveAndFlush(restaurant);

	}

	@GetMapping("/{id}")
	public Restaurant getRestaurant(@PathVariable Long id) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		if (restaurant.isPresent()) {
			return restaurant.get();
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public Iterable<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	@GetMapping("/search/{zipcode}")
	public Iterable<Restaurant> searchRestaurants(@RequestParam String zipcode, @RequestParam String allergy) {
		validateZipCode(zipcode);

		Iterable<Restaurant> restaurants = Collections.emptyList();
		if (allergy.equalsIgnoreCase("peanut")) {
			restaurants = restaurantRepository.findRestaurantsByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(zipcode);
		} else if (allergy.equalsIgnoreCase("dairy")) {
			restaurants = restaurantRepository.findRestaurantsByZipCodeAndDairyScoreNotNullOrderByDairyScore(zipcode);
		} else if (allergy.equalsIgnoreCase("egg")) {
			restaurants = restaurantRepository.findRestaurantsByZipCodeAndEggScoreNotNullOrderByEggScore(zipcode);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		return restaurants;
	}

	private void validateNewRestaurant(Restaurant restaurant) {
		if (ObjectUtils.isEmpty(restaurant.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		validateZipCode(restaurant.getZipCode());

		Optional<Restaurant> existingRestaurant = restaurantRepository
				.findRestaurantsByNameAndZipCode(restaurant.getName(), restaurant.getZipCode());
		if (existingRestaurant.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}

	private void validateZipCode(String zipcode) {
		if (!zipCodePattern.matcher(zipcode).matches()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
}
