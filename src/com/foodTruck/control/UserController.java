package com.foodTruck.control;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodTruck.data.UserAccount;
import com.foodTruck.db.gateway.TableDataGateway;

public class UserController {

	public static int registerUser(HttpServletRequest request, HttpServletResponse response) {
		UserAccount userAcc = new UserAccount();
		userAcc.setUserFirstName(request.getParameter("firstName"));
		userAcc.setUserLastName(request.getParameter("lastName"));
		userAcc.setUserType(request.getParameter("userType"));
		userAcc.setLoginName(request.getParameter("loginName"));
		userAcc.setLogin_password(request.getParameter("password"));
		userAcc.setUserPhoneNumber(Long.parseLong(request.getParameter("phoneNumber")));
		userAcc.setUserEmailAddress(request.getParameter("emailAddress"));
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

	public static void signUpUser(HttpServletRequest request, HttpServletResponse response) {
		UserAccount userAcc = new UserAccount();
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		// Triggers call to the tabledatagateway for verify credentials
		userAcc = TableDataGateway.signUpUser(loginName, password, userType);
	}

}
