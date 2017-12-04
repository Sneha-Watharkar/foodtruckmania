package control;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import data.FoodTruck;
import data.UserAccount;
import dbgateway.TableDataGateway;

public class LocationController {

	private static final String STATUS_PENDING = "Pending";
	

	public static int reserveLocation(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		FoodTruck foodTruck = new FoodTruck();

		HttpSession session = request.getSession();
	    UserAccount user = (UserAccount) session.getAttribute("user");
	    int userId = user.getUserId();
		foodTruck.setFoodTruckLocation(data.getString("location"));
		convertLocationToCoordinates(foodTruck);
		JSONArray daysJSon = data.getJSONArray("days");
		StringBuffer sb = new StringBuffer();
		if(daysJSon!=null && daysJSon.length()>0) {
			for(int i=0;i<daysJSon.length();i++) {
				sb.append(daysJSon.getString(i));
				sb.append("-");
			}
		}
		foodTruck.setFoodTruckDay(sb.toString());
		foodTruck.setFoodTruckTime(data.getString("timeSlot"));
		int dataInserted = TableDataGateway.reserveLocation(userId, foodTruck);
		return dataInserted;
	}

	/*
	 * updates latitude and longitude coordiantes for each of the truck location.
	 */
	private static void convertLocationToCoordinates(FoodTruck foodTruck) {
		if (foodTruck.getFoodTruckLocation().equals("Prospector")) {
			foodTruck.setLatitude((float) 35.3069394);
			foodTruck.setLongitude((float) -80.7354401);
		} else if (foodTruck.getFoodTruckLocation().equals("Colvard Hall")) {
			foodTruck.setLatitude((float) 35.306703);
			foodTruck.setLongitude((float) -80.730515);

		} else if (foodTruck.getFoodTruckLocation().equals("Student Activity Center")) {
			foodTruck.setLatitude((float) 35.304362);
			foodTruck.setLongitude((float) -80.732019);

		} else if (foodTruck.getFoodTruckLocation().equals("Student Union")) {
			foodTruck.setLatitude((float) 35.306667);
			foodTruck.setLongitude((float) -80.735256);

		} else if (foodTruck.getFoodTruckLocation().equals("Van Landingham / John Kick")) {
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

	public static ArrayList<FoodTruck> getAllLocations(HttpServletRequest request, HttpServletResponse response,
			JSONObject data) {
		return TableDataGateway.getAllLocations();
	}

}

