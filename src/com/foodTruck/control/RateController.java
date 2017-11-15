package com.foodTruck.control;

import java.util.Date;

import com.foodTruck.data.FoodTruckRating;

public class RateController {
	
	public int rateTruck(int userId,int rating,String foodTruckId,String comments) {
		FoodTruckRating ratingObj = new FoodTruckRating();
		ratingObj.setRatingDate(new Date());
		ratingObj.setRating(rating);
		ratingObj.setComments(comments);
		return 0;
		
	}
	
	public FoodTruckRating viewFeedback(int foodTruckId) {
		FoodTruckRating ratingObj = new FoodTruckRating();
		return ratingObj;
	}

}
