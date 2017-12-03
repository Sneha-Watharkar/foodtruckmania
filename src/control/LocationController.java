package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import data.FoodTruck;
import data.UserAccount;
import dbgateway.TableDataGateway;

public class LocationController {

	private static final String STATUS_PENDING = "Pending";
	
	private static final String STATUS_ACTIVE = "Active";

	public static int reserveLocation(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		FoodTruck foodTruck = new FoodTruck();

		int userId = 28;
		foodTruck.setFoodTruckLocation(String.valueOf(data.getInt("location")));
		convertLocationToCoordinates(foodTruck);
		JSONArray daysJSon = data.getJSONArray("days");
		if(daysJSon!=null && daysJSon.length()>0) {
			for(int i=0;i<daysJSon.length();i++) {
				foodTruck.setFoodTruckDay(daysJSon.getString(i));
			}
		}
		foodTruck.setFoodTruckTime(data.getString("timeSlot"));
		foodTruck.setFoodTruckStatus(STATUS_PENDING);
		int dataInserted = TableDataGateway.reserveLocation(userId, foodTruck);
		return dataInserted;
	}

	/*
	 * updates latitude and longitude coordiantes for each of the truck location.
	 */
	private static void convertLocationToCoordinates(FoodTruck foodTruck) {
		if (foodTruck.getFoodTruckLocation().equals("1")) {
			foodTruck.setLatitude((float) 35.3069394);
			foodTruck.setLongitude((float) -80.7354401);
		} else if (foodTruck.getFoodTruckLocation().equals("2")) {
			foodTruck.setLatitude((float) 35.306703);
			foodTruck.setLongitude((float) -80.730515);

		} else if (foodTruck.getFoodTruckLocation().equals("3")) {
			foodTruck.setLatitude((float) 35.304362);
			foodTruck.setLongitude((float) -80.732019);

		} else if (foodTruck.getFoodTruckLocation().equals("4")) {
			foodTruck.setLatitude((float) 35.306667);
			foodTruck.setLongitude((float) -80.735256);

		} else if (foodTruck.getFoodTruckLocation().equals("5")) {
			foodTruck.setLatitude((float) 35.307886);
			foodTruck.setLongitude((float) -80.733716);

		} else {
			foodTruck.setLatitude((float) 35.307400);
			foodTruck.setLongitude((float) -80.724969);
		}

	}

	public static FoodTruck locateTruck(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		JSONObject truckJson = data.getJSONObject("truckDetails");
		FoodTruck foodTruck = new FoodTruck();
		foodTruck.setFoodTruckName(truckJson.getString("truckname"));
		foodTruck.setFoodTruckId(truckJson.getInt("foodTruckId"));
		TableDataGateway.locateTruck(foodTruck);
		return foodTruck;
	}

}

