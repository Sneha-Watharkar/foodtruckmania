package com.foodTruck.data;

import java.io.Serializable;
import java.util.Date;

public class FoodTruck implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int foodTruckId;
	private String foodTruckName;
	private String foodTruckLocation;
	private Date foodTruckTime;
	private String foodTruckMenu;
	private String foodTruckStatus;

	public int getFoodTruckId() {
		return foodTruckId;
	}

	public void setFoodTruckId(int foodTruckId) {
		this.foodTruckId = foodTruckId;
	}

	public String getFoodTruckName() {
		return foodTruckName;
	}

	public void setFoodTruckName(String foodTruckName) {
		this.foodTruckName = foodTruckName;
	}

	public String getFoodTruckLocation() {
		return foodTruckLocation;
	}

	public void setFoodTruckLocation(String foodTruckLocation) {
		this.foodTruckLocation = foodTruckLocation;
	}

	public Date getFoodTruckTime() {
		return foodTruckTime;
	}

	public void setFoodTruckTime(Date foodTruckTime) {
		this.foodTruckTime = foodTruckTime;
	}

	public String getFoodTruckMenu() {
		return foodTruckMenu;
	}

	public void setFoodTruckMenu(String foodTruckMenu) {
		this.foodTruckMenu = foodTruckMenu;
	}

	public String getFoodTruckStatus() {
		return foodTruckStatus;
	}

	public void setFoodTruckStatus(String foodTruckStatus) {
		this.foodTruckStatus = foodTruckStatus;
	}

}
