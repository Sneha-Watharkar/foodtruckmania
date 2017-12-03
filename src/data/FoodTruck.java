package data;

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
	private String foodTruckDay;
	private String foodTruckTime;
	private Float latitude;
	private Float longitude;
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
	
	public String getFoodTruckDay() {
		return foodTruckDay;
	}

	public void setFoodTruckDay(String foodTruckDay) {
		this.foodTruckDay = foodTruckDay;
	}


	public String getFoodTruckTime() {
		return foodTruckTime;
	}

	public void setFoodTruckTime(String foodTruckTime) {
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

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

}

