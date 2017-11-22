package control;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.FoodTruck;
import dbgateway.TableDataGateway;

public class LocationController {

	private static final String STATUS_PENDING = "Pending";
	
	private static final String STATUS_ACTIVE = "Active";

	public static int reserveLocation(HttpServletRequest request, HttpServletResponse response) {
		FoodTruck foodTruck = new FoodTruck();

		String userId = request.getParameter("emailAddress");
		foodTruck.setFoodTruckName(request.getParameter("foodTruckName"));
		foodTruck.setFoodTruckLocation(request.getParameter("location"));
		// foodTruck.setFoodTruckTime(Date.parse((request.getParameter("dateTime")));
		foodTruck.setLatitude(Float.valueOf(request.getParameter("latitude")));
		foodTruck.setLongitude(Float.valueOf(request.getParameter("longitude")));
		foodTruck.setFoodTruckStatus(STATUS_PENDING);
		int dataInserted = TableDataGateway.reserveLocation(userId, foodTruck);
		return dataInserted;
	}

	private ArrayList[] convertLocationToCoordinates() {
		ArrayList[] locCoordinates = new ArrayList[2];
		return locCoordinates;
	}

	public int reserveTimeSlot(int foodTruckId, Date timeslot) {
		return 0;
	}

	public void locateTruck(int userId, int foodTruckId) {

	}

}
