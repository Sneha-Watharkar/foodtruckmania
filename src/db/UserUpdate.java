package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import data.FoodTruck;
import data.UserAccount;
import dbgateway.TableDataGateway;
import util.ConnectionPool;
import util.DBUtil;
import util.ConnectionPool2;

public class UserUpdate {

	public static int registerUser(UserAccount userAcc) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		String query = "INSERT INTO UserAccount (userFirstName, userLastName, userType,loginName,password,userPhoneNumber,userEmailAddress,userAlertPreference) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setString(1, userAcc.getUserFirstName());
			ps.setString(2, userAcc.getUserLastName());
			ps.setString(3, userAcc.getUserType());
			ps.setString(4, userAcc.getLoginName());
			ps.setString(5, userAcc.getLogin_password());
			ps.setString(6, String.valueOf(userAcc.getUserPhoneNumber()));
			ps.setString(7, userAcc.getUserEmailAddress());
			ps.setString(8, "text=0&email=0");
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			// pool.freeConnection(connection);
		}

	}
	
	public static int registerFoodTruck(UserAccount userAcc,String foodTruckName) {
		PreparedStatement ps = null;
		String query = "INSERT INTO foodtruck(userID, foodTruckName,foodTruckStatus) "
				+ "VALUES (?,?,?)";
		try {
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setInt(1, getUserID(userAcc).getUserId());
			ps.setString(2, foodTruckName);
			ps.setString(3, "Pending");
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
		}

	}
	
	public static ArrayList<FoodTruck> getAllFoodTrucks() {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "Select * FROM foodtruck WHERE foodTruckStatus='approve'";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			System.out.println("Result set is :" + rs.getRow());
			ArrayList<FoodTruck> foodTruckList = new ArrayList<FoodTruck>();
			while (rs.next()) {
				FoodTruck truck = new FoodTruck();
				truck.setFoodTruckId(rs.getInt("foodTruckId"));
				truck.setFoodTruckName(rs.getString("foodtruckName"));
				truck.setLatitude(rs.getFloat("latitude"));
				truck.setLongitude(rs.getFloat("longitude"));
				truck.setFoodTruckTime(rs.getString("truckTime"));
				truck.setFoodTruckMenu(rs.getString("foodTruckMenu"));
				truck.setFoodTruckStatus(rs.getString("foodTruckStatus"));
				foodTruckList.add(truck);
			}
			return foodTruckList;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closePreparedStatement(ps);
			// pool.freeConnection(connection);
		}

	}
	public static FoodTruck getFoodTrucks(int userId) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "Select * FROM foodtruck WHERE userId=?";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			System.out.println("Result set is :" + rs.getRow());
			ArrayList<FoodTruck> foodTruckList = new ArrayList<FoodTruck>();
			FoodTruck truck = new FoodTruck();
			if (rs.next()) {
				truck.setFoodTruckId(rs.getInt("foodTruckId"));
				truck.setFoodTruckName(rs.getString("foodtruckName"));
				truck.setLatitude(rs.getFloat("latitude"));
				truck.setLongitude(rs.getFloat("longitude"));
				truck.setFoodTruckTime(rs.getString("truckTime"));
				truck.setFoodTruckMenu(rs.getString("foodTruckMenu"));
				truck.setFoodTruckStatus(rs.getString("foodTruckStatus"));
				foodTruckList.add(truck);
			}
			return truck;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closePreparedStatement(ps);
			// pool.freeConnection(connection);
		}

	}
	
	
	public static UserAccount getUserID(UserAccount userAcc) {
		
		PreparedStatement ps = null;
		String query = "SELECT userId FROM UserAccount " +  "where loginName = ?";
		try {
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setString(1, userAcc.getLoginName());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userAcc.setUserId(rs.getInt("userId"));
				return userAcc;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
		

	}
	public static UserAccount selectUserForLogin(String loginName, String password) {
		// ConnectionPool pool = ConnectionPool.getInstance();
		// Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		
		String query = "SELECT * FROM UserAccount WHERE loginName = ? AND Password = ?";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println("Select query is:" + query);
			ps = connection.prepareStatement(query);
			ps.setString(1, loginName); 
			ps.setString(2, password);
			rs = ps.executeQuery();
			System.out.println("Result set is :" + rs.getRow());
			UserAccount userAcc = null;
			if (rs.next()) {
				userAcc = new UserAccount();
				userAcc.setUserId(rs.getInt("userId"));
				userAcc.setUserFirstName(rs.getString("userFirstName"));
				userAcc.setUserLastName(rs.getString("userLastName"));
				userAcc.setUserType(rs.getString("userType"));
				userAcc.setLoginName(rs.getString("loginName"));
				userAcc.setLogin_password(rs.getString("password"));
				userAcc.setUserPhoneNumber(Long.parseLong((rs.getString("userPhoneNumber"))));
				userAcc.setUserEmailAddress(rs.getString("userEmailAddress"));
			}
			return userAcc;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
		}
	}
	
	public static UserAccount getUserAlerts(int userId) {
		// ConnectionPool pool = ConnectionPool.getInstance();
		// Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		
		String query = "SELECT userAlertPreference FROM UserAccount " +  "where userId = ?";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println("Select query is:" + query);
			ps = connection.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			System.out.println("Result set is :" + rs.getRow());
			UserAccount userAcc = null;
			if (rs.next()) {
				userAcc = new UserAccount();
				userAcc.setUserAlertPreference(rs.getString("userAlertPreference"));
			}
			return userAcc;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} catch(ClassNotFoundException ex){
			System.out.println(ex);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
		}
	}

	// Made it hashmap instead of arraylist. For each userIdinsert the food truck
	// info.
	public static int approveVendorRequests(ArrayList vendorLists) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "UPDATE FoodTruck set foodTruckStatus=?" + "where foodTruck_id=?";
		/*
		 * To add code to iterate the foodtruckstatus to active for(FoodTruck
		 * fdTruck:vendorLists) { fdTruck = new FoodTruck(); }
		 */

		return 0;

	}

	public static int updateAlerts(UserAccount userAcc) {
		PreparedStatement ps = null;
		String query = "UPDATE UserAccount SET userAlertPreference = ? WHERE userID=?";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setString(1,userAcc.getUserAlertPreference());
			ps.setInt(2, userAcc.getUserId());
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}

	public static ArrayList<String> getEmailAddress(int foodTruckId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query ="SELECT userEmailAddress from UserAccount WHERE userID IN (SELECT userID from UserFavorites WHERE foodTruckId = ?)";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			ArrayList<String> emails = new ArrayList<String>();
			System.out.println(ps.toString());
			ps.setInt(1, foodTruckId);
			rs  = ps.executeQuery();
			while(rs.next()){
				emails.add(rs.getString("userEmailAddress"));
			}
			return emails;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}
	
	public static void getFoodTruckId(UserAccount userAcc) {
		PreparedStatement ps = null;
		String query = "SELECT foodTruckId FROM FoodTruck " +  "where userId = ?";
		try {
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setInt(1, userAcc.getUserId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userAcc.setTruckId(rs.getInt("foodTruckId"));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBUtil.closePreparedStatement(ps);
		}

		
	}
}
