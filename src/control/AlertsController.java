package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import data.UserAccount;
import dbgateway.TableDataGateway;

public class AlertsController {
	public static String getAlerts(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		System.out.println("Inside get Alerts");
		/*JSONObject userJson = data.getJSONObject("userDetails");*/
		HttpSession session = request.getSession();
	    UserAccount user = (UserAccount) session.getAttribute("user");
		int userId = user.getUserId();

		String alerts = TableDataGateway.getUserAlerts(userId);
		
		return alerts;

	}
	public static int insertAlerts(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		System.out.println("Inside insert alerts");
		HttpSession session = request.getSession();
	    UserAccount user = (UserAccount) session.getAttribute("user");

		int userId = user.getUserId();
		Boolean text = data.getBoolean("sms");
		Boolean email = data.getBoolean("email");
		String alerts = null;
		if(text & email){
			alerts = "text=1&email=1";
		}
		else if(email){
			alerts = "text=0&email=1";
		}
		else if(text){
			alerts = "text=1&email=0";
		}
		else{
			alerts = "text=0&email=0";
		}
		System.out.println(alerts);
		int success = TableDataGateway.updateAlerts(userId,alerts);
		
		return success;

	}
	/*
	 * User selects any specific foodtruck and sets up as 	a favorite.Triggers call to TableDataGateway.
	 */
	public static int updateUserFav(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		JSONObject truckJson = data.getJSONObject("truck");
		HttpSession session = request.getSession();
	    UserAccount user = (UserAccount) session.getAttribute("user");
		int userId = user.getUserId();
		int foodTruckId = truckJson.getInt("foodTruckId");
		int favUpdate = TableDataGateway.updateUserFav(userId,foodTruckId);
		return favUpdate;
	}
	
}
