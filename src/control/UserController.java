package control;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import data.UserAccount;
import dbgateway.TableDataGateway;

public class UserController {

	public static int registerUser(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
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

	private ArrayList fetchPendingApprovals() {
		ArrayList pendingApprovals = new ArrayList();
		return pendingApprovals;

	}

	public int approveFoodTruckRequests() {
		return 0;
	}

	public static UserAccount login(HttpServletRequest request, HttpServletResponse response,JSONObject data) {
		System.out.println("Inside register User");
		JSONObject userJson = data.getJSONObject("userDetails");
		UserAccount userAcc = new UserAccount();
		userAcc.setLoginName(userJson.getString("email"));
		userAcc.setLogin_password(userJson.getString("password"));
		// Triggers call to the tabledatagateway for verify credentials
		userAcc = TableDataGateway.loginUser(userAcc.getLoginName(), userAcc.getLogin_password());
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

}
