package com.foodTruck.db.gateway;

import com.foodTruck.data.UserAccount;
import com.foodTruck.db.UserUpdate;

public class TableDataGateway {

	public static int registerUser(UserAccount userAcc) {
		int isDataInserted = 0;
		//Delegate call to UserUpdate class for inserting into the dB.
		isDataInserted = UserUpdate.registerUser(userAcc);
		return isDataInserted;
	}

	public static UserAccount signUpUser(String loginName, String password, String userType) {
		UserAccount userAcc = new UserAccount();
		//Delegate call to the UserUpdate class to verify user credentials.
		userAcc = UserUpdate.selectUserForLogin(loginName, password, userType);
		return userAcc;
	}

}
