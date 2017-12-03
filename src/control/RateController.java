package control;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import data.FoodTruckRating;
import data.UserAccount;
import dbgateway.TableDataGateway;

public class RateController {
	
	/*
	 * This method prepares the foodTruck Rating object to insert user ratings.
	 */
	public static int rateTruck(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		JSONObject rateJson = data.getJSONObject("truckDetails");
		UserAccount userAcc = (UserAccount) request.getAttribute("user");
		FoodTruckRating ratingObj = new FoodTruckRating();
		ratingObj.setRatingDate(new Date());
		ratingObj.setRating(rateJson.getInt("rating"));
		ratingObj.setComments(rateJson.getString("comments"));
		ratingObj.setFoodTruckId(rateJson.getInt("foodTruckId"));
		int insertData = TableDataGateway.rateTruck(userAcc.getUserId(),ratingObj);
		return insertData;
	}
	
	/*
	 * This method retrieves all the feedback for any given food truck.
	 */
	public static ArrayList<FoodTruckRating> getFeedback(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		JSONObject rateJson = data.getJSONObject("truckDetails");
		ArrayList<FoodTruckRating> ratingLists = new ArrayList<>();
		int foodTruckId = rateJson.getInt("foodTruckId");
		ratingLists = TableDataGateway.getFeedback(foodTruckId);
		return ratingLists;
	}

	

}
