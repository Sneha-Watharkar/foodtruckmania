package control;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import data.FoodTruckRating;
import data.UserAccount;
import dbgateway.TableDataGateway;

public class RateController {
	
	/*
	 * This method prepares the foodTruck Rating object to insert user ratings.
	 */
	public static int rateTruck(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		JSONObject truckJson = data.getJSONObject("truckName");
		HttpSession session = request.getSession();
	    UserAccount user = (UserAccount) session.getAttribute("user");
		FoodTruckRating ratingObj = new FoodTruckRating();
		ratingObj.setRatingDate(new Date());
		ratingObj.setRating(data.getInt("rating"));
		ratingObj.setComments(data.getString("comments"));
		ratingObj.setFoodTruckId(truckJson.getInt("truckId"));
		int insertData = TableDataGateway.rateTruck(user.getUserId(),ratingObj);
		/*if (ratingObj.getRating() > 2){
			TableDataGateway.updateUserFav(user.getUserId(), truckJson.getInt("truckId"));
		}*/
		return insertData;
	}
	
	/*
	 * This method retrieves all the feedback for any given food truck.
	 */
	public static ArrayList<FoodTruckRating> getFeedback(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		HttpSession session = request.getSession();
	    UserAccount user = (UserAccount) session.getAttribute("user");
		ArrayList<FoodTruckRating> ratingLists = new ArrayList<>();
		int userId = user.getUserId();
		ratingLists = TableDataGateway.getFeedback(userId);
		return ratingLists;
	}

	

}
