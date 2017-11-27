package dbgateway;

import java.util.ArrayList;

import data.FoodTruck;
import data.UserAccount;
import db.AdminUpdate;
import db.LocationUpdate;
import db.UserUpdate;

public class TableDataGateway {
	
	//Adding connection string here. Not reading from context.xml file. Once dev work is complete can check that.
	public static final String connectionString = "jdbc:sqlserver://uncc-anderson.database.windows.net:1433;"
			+ "database=FoodTruckMania;" + "user=durga;" + "password=Uncc2017;" + "encrypt=true;"
			+ "trustServerCertificate=false;" + "hostNameInCertificate=*.database.windows.net;"
			+ "loginTimeout=30;";

	public static int registerUser(UserAccount userAcc) {
		int isDataInserted = 0;
		//Delegate call to UserUpdate class for inserting into the dB.
		isDataInserted = UserUpdate.registerUser(userAcc);
		//userAcc = UserUpdate.selectUserForLogin("newUser", "testPwd1");
		return isDataInserted;
	}

	public static UserAccount signUpUser(String firstName, String lastName, String email, String password, String type, long phone) {
		UserAccount userAcc = new UserAccount();
		userAcc.setUserFirstName(firstName);
		userAcc.setLogin_password(password);
		userAcc.setLoginName(email);
		userAcc.setUserType(type);
		userAcc.setUserPhoneNumber(phone);
		//Delegate call to the UserUpdate class to verify user credentials.
		int result = UserUpdate.registerUser(userAcc);
		System.out.println(result);
		return userAcc;
	}
	public static UserAccount loginUser(String loginName, String password) {
		UserAccount userAcc = new UserAccount();
		//Delegate call to the UserUpdate class to verify user credentials.
		userAcc = UserUpdate.selectUserForLogin(loginName, password);
		return userAcc;
	}
	public static int approveRequests(int foodTruckId, String foodTruckStatus) {
		int success = AdminUpdate.updateFoodTruckStatus(foodTruckId, foodTruckStatus);
		return success;
	}

	public static ArrayList<FoodTruck> fetchPendingApprovals() {
		return AdminUpdate.fetchPendingApprovals();
	}

	public static int registerFoodTruck(UserAccount userAcc) {
		return UserUpdate.registerFoodTruck(userAcc);
	}

}

