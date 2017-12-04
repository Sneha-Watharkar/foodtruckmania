package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import data.FoodTruck;
import data.UserAccount;
import db.AdminUpdate;
import db.UserUpdate;
import dbgateway.TableDataGateway;

public class UserController {

	public static int registerUser(HttpServletRequest request, HttpServletResponse response, JSONObject data ) {
		System.out.println("Inside register User");
		UserAccount userAcc = new UserAccount();
		JSONObject userJson = data.getJSONObject("userDetails");
		userAcc.setUserFirstName(userJson.getString("firstname"));
		userAcc.setUserLastName(userJson.getString("lastname"));
		userAcc.setUserType(userJson.getString("type"));
		userAcc.setLoginName(userJson.getString("email"));
		userAcc.setLogin_password(userJson.getString("password"));
		userAcc.setUserPhoneNumber(Long.parseLong(userJson.getString("phone")));
		userAcc.setUserEmailAddress(userJson.getString("email"));

		int success = TableDataGateway.registerUser(userAcc);
		if(success == 1 && userAcc.getUserType().equals("vendor")){
			JSONObject truckJson = data.getJSONObject("truckDetails");
			String foodTruckName = truckJson.getString("name");
			TableDataGateway.registerFoodTruck(userAcc,foodTruckName);
		}
		System.out.println("Success : "+success);
		return success;
	}
	public static Map<Integer,String> fetchPendingApprovals() {
		ArrayList<FoodTruck> pendingApprovals = TableDataGateway.fetchPendingApprovals();
		Map<Integer,String> results = new HashMap<Integer, String>();
		for (FoodTruck userAcc: pendingApprovals){
			results.put(userAcc.getFoodTruckId(), userAcc.getFoodTruckName());
		}
		return results;
	}
	public static ArrayList<FoodTruck> getAllFoodTrucks(){
		return TableDataGateway.getAllFoodTrucks();
	}
	
	public static int approveFoodTruckRequests(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		JSONObject userJson = data.getJSONObject("userDetails");
		UserAccount userAcc = new UserAccount();
		int foodTruckId = userJson.getInt("foodTruckId");
		String foodTruckStatus = userJson.getString("foodTruckStatus");
		int success = TableDataGateway.approveRequests(foodTruckId, foodTruckStatus);
		return success;
	}
	public static UserAccount login(HttpServletRequest request, HttpServletResponse response,JSONObject data) {
		System.out.println("Inside Login User");
		UserAccount userAcc = new UserAccount();
		userAcc.setLoginName(data.getString("name"));
		userAcc.setLogin_password(data.getString("password"));
		userAcc = TableDataGateway.loginUser(userAcc.getLoginName(), userAcc.getLogin_password());
		if (userAcc != null){
			System.out.println("User Details of login: LoginName: " + userAcc.getLoginName() + "FirstName: "+ userAcc.getUserFirstName() + "Last Name: " + userAcc.getUserLastName());
		}
		return userAcc;
	}
	/*
	 * Method for test purpose
	public static void registerUser() {
		UserAccount userAcc = new UserAccount();
		// Hardcoding userAccount to chec database inserts
		userAcc.setUserFirstName("Test");
		userAcc.setUserLastName("User1");
		userAcc.setUserType("Customer");
		userAcc.setLoginName("newUser");
		userAcc.setLogin_password("testPwd1");
		userAcc.setUserPhoneNumber(Long.valueOf("1234567890"));
		userAcc.setUserEmailAddress("testUser1@email.com");
		int success = TableDataGateway.registerUser(userAcc);
		System.out.println("return success message:" + success);

	}*/
	public static ArrayList<FoodTruck> getFavFoodTrucks(HttpServletRequest request, HttpServletResponse response,
			JSONObject data) {
		
		ArrayList<FoodTruck> results = TableDataGateway.getFavFoodTrucks(data.getInt("userID"));
		return results;
	}

}
