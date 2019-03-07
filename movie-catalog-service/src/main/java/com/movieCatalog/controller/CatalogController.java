/**
 * 
 */
package com.movieCatalog.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.movieCatalog.entity.CatalogItem;
import com.movieCatalog.entity.Movie;
import com.movieCatalog.entity.Rating;
import com.movieCatalog.entity.UserRating;

/**
 * @author Bright
 *
 */
@RestController
@RequestMapping("/catalog")
public class CatalogController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	
	  @RequestMapping("/{userId}") 
	  public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {	  
	  
	  UserRating userRating= restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratings/users/"+userId, UserRating.class);
	  
	  return userRating.getRatings().stream() 
			  .map(rating -> { 
				  Movie movie=restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/"+rating.getMovieId(), Movie.class); 
				  return new CatalogItem(movie.getName(), "desc",rating.getRating());
				  
				}).collect(Collectors.toList());
	  
	  
	  //return Collections.singletonList( new CatalogItem("Andhadhun", "Ak", 5) );
	  
	  }
	 

	/*
	 * @RequestMapping("/{userId}") public List<CatalogItem>
	 * getCatalog(@PathVariable("userId") String userId) {
	 * 
	 * List<Rating> ratingsList = Arrays.asList(new Rating("1234", 3), new
	 * Rating("5678", 4));
	 * 
	 * return ratingsList.stream().map(rating -> {
	 * 
	 * Movie movie=webClientBuilder.build() .get()
	 * .uri("http://localhost:8082/movies/"+rating.getMovieId()) .retrieve()
	 * .bodyToMono(Movie.class) .block();
	 * 
	 * return new CatalogItem(movie.getName(), "desc", rating.getRating());
	 * }).collect(Collectors.toList());
	 * 
	 *  
	 * 
	 * }
	 */
}
