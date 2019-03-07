package com.ratingsData.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingsData.entity.Rating;
import com.ratingsData.entity.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getRatingsPerUser(@PathVariable("userId") String userId) {
		List<Rating> ratingsList = Arrays.asList( new Rating("1234", 3), 
				  new Rating("5678", 4) 
				  );
		UserRating userRating=new UserRating();
		userRating.setRatings(ratingsList);
		
		return userRating;				
	}
}
