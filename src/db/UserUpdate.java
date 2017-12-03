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
		String query = "INSERT INTO UserAccount (userFirstName, userLastName, userType,loginName,password,userPhoneNumber,userEmailAddress) "
				+ "VALUES (?,?,?,?,?,?,?)";
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
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			// pool.freeConnection(connection);
		}

	}
	
	public static int registerFoodTruck(UserAccount userAcc) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		String query = "INSERT INTO foodtruck(userID, foodTruckName,foodTruckStatus) "
				+ "VALUES (?,?,?)";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setInt(1, getUserID(userAcc).getUserId());
			ps.setString(2, userAcc.getUserFirstName() + userAcc.getUserLastName());
			ps.setString(3, "Pending");
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			// pool.freeConnection(connection);
		}

	}
	
	public static ArrayList<FoodTruck> getAllFoodTrucks() {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "Select * FROM foodtruck WHERE foodTruckStatus='approved'";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			System.out.println("Result set is :" + rs.getRow());
			ArrayList<FoodTruck> foodTruckList = new ArrayList<FoodTruck>();
			if (rs.next()) {
				FoodTruck truck = new FoodTruck();
				truck.setFoodTruckId(rs.getInt("foodTruckId"));
				truck.setFoodTruckName(rs.getString("foodtruckName"));
				truck.setLatitude(rs.getFloat("latitude"));
				truck.setLongitude(rs.getFloat("truckTime"));
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
	
	
	public static UserAccount getUserID(UserAccount userAcc) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		String query = "SELECT userId FROM UserAccount " +  "where loginName = ?";
		try {
			Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			//Connection connection = ConnectionPool2.getConnection();
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
			// pool.freeConnection(connection)
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
	
	public static UserAccount getUserAlerts(String loginName) {
		// ConnectionPool pool = ConnectionPool.getInstance();
		// Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		
		String query = "SELECT FROM UserAccount " +  "where loginName = ?";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println("Select query is:" + query);
			ps = connection.prepareStatement(query);
			ps.setString(1, loginName);
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
			ps.setInt(1, getUserID(userAcc).getUserId());
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}
}
