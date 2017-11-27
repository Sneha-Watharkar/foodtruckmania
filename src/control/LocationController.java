package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.FoodTruck;
import dbgateway.TableDataGateway;

public class LocationController {

	private static final String STATUS_PENDING = "Pending";
	
	private static final String STATUS_ACTIVE = "Active";

	public static int reserveLocation(HttpServletRequest request, HttpServletResponse response) {
		FoodTruck foodTruck = new FoodTruck();

		UserAccount userAcc = (UserAccount) request.getAttribute("user");
		JSONObject truckJson = data.getJSONObject("truckDetails");
		foodTruck.setFoodTruckName(truckJson.getString("firstname"));
		int userId = userAcc.getUserId();
		foodTruck.setFoodTruckLocation(truckJson.getString("truckLocation"));
		convertLocationToCoordinates(foodTruck);
		foodTruck.setFoodTruckTime(truckJson.getString("dateTime"));
		foodTruck.setFoodTruckStatus(STATUS_PENDING);
		int dataInserted = TableDataGateway.reserveLocation(userId, foodTruck);
		return dataInserted;
	}

	private static void convertLocationToCoordinates(FoodTruck foodTruck) {
		if (foodTruck.getFoodTruckLocation().equals("Opp. Student Union")) {
			foodTruck.setLatitude((float) 35.3069394);
			foodTruck.setLongitude((float) -80.7354401);
		} else if (foodTruck.getFoodTruckLocation().equals("Richard Stadium")) {
			foodTruck.setLatitude((float) 35.306703);
			foodTruck.setLongitude((float) -80.730515);

		} else if (foodTruck.getFoodTruckLocation().equals("Grigg Hall")) {
			foodTruck.setLatitude((float) 35.304362);
			foodTruck.setLongitude((float) -80.732019);

		} else if (foodTruck.getFoodTruckLocation().equals("Portal")) {
			foodTruck.setLatitude((float) 35.306667);
			foodTruck.setLongitude((float) -80.735256);

		} else if (foodTruck.getFoodTruckLocation().equals("Cato Hall")) {
			foodTruck.setLatitude((float) 35.307886);
			foodTruck.setLongitude((float) -80.733716);

		} else {
			foodTruck.setLatitude((float) 35.307400);
			foodTruck.setLongitude((float) -80.724969);
		}

	}

	public void locateTruck(int userId, int foodTruckId) {

	}

}

