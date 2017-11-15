package com.foodTruck.data;

import java.io.Serializable;
import java.util.Date;

public class FoodTruckRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int foodTruckId;
	private Date ratingDate;
	private int rating;
	private String comments;

	public int getFoodTruckId() {
		return foodTruckId;
	}

	public void setFoodTruckId(int foodTruckId) {
		this.foodTruckId = foodTruckId;
	}

	public Date getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(Date ratingDate) {
		this.ratingDate = ratingDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
