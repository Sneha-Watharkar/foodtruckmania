package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import data.UserAccount;
import dbgateway.TableDataGateway;

public class AlertsController {
	public static String getAlerts(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		System.out.println("Inside get Alerts");
		JSONObject userJson = data.getJSONObject("userDetails");

		String loginName = userJson.getString("email");

		String alerts = TableDataGateway.getUserAlerts(loginName);
		
		return alerts;

	}
	public static int insertAlerts(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		System.out.println("Inside insert alerts");
		JSONObject userJson = data.getJSONObject("userDetails");

		String loginName = userJson.getString("email");
		String alerts = userJson.getString("userAlertPreference");

		int success = TableDataGateway.updateAlerts(loginName,alerts);
		
		return success;

	}
	/*
	 * User selects any specific foodtruck and sets up as a favorite.Triggers call to TableDataGateway.
	 */
	public static int updateUserFav(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		JSONObject userFavJson = data.getJSONObject("userFav");
		int userId = userFavJson.getInt("userId");
		int foodTruckId = userFavJson.getInt("foodTruckId");
		int favUpdate = TableDataGateway.updateUserFav(userId,foodTruckId);
		return favUpdate;
	}
	
}
