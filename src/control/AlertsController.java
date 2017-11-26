package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import data.UserAccount;
import dbgateway.TableDataGateway;

public class AlertsController {
	public static String getAlerts(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		System.out.println("Inside register User");
		JSONObject userJson = data.getJSONObject("userDetails");

		String loginName = userJson.getString("email");

		String alerts = TableDataGateway.getUserAlerts(loginName);
		
		return alerts;

	}
	public static int insertAlerts(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		System.out.println("Inside register User");
		JSONObject userJson = data.getJSONObject("userDetails");
		UserAccount userAcc = new UserAccount();

		userAcc.setUserFirstName(userJson.getString("firstname"));
		userAcc.setUserLastName(userJson.getString("lastname"));
		userAcc.setUserType(userJson.getString("type"));
		userAcc.setLoginName(userJson.getString("email"));
		userAcc.setLogin_password(userJson.getString("password"));
		userAcc.setUserPhoneNumber(Long.parseLong(userJson.getString("phone")));
		userAcc.setUserEmailAddress(userJson.getString("email"));

		int success = TableDataGateway.registerUser(userAcc);
		
		return success;

	}
	
}
