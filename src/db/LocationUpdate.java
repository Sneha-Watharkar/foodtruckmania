package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import data.FoodTruck;
import dbgateway.TableDataGateway;
import util.ConnectionPool2;
import util.DBUtil;

public class LocationUpdate {

	public static int reserveLocation(int userId, FoodTruck foodTruck) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		String query = "UPDATE FoodTruck set " + "latitude=?," + "longitude = ?," + "foodTruckDay = ?,"
		+ "truckTime = ?, " + "truckLocation = ? " + "  where userId = ?";
		/*String query = "INSERT INTO FoodTruck (userId, latitude, longitude, truckTime, foodTruckStatus) "
				+ "VALUES (?,?,?,?,?,?)";*/
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setString(1, String.valueOf(foodTruck.getLatitude()));
			ps.setString(2, String.valueOf(foodTruck.getLongitude()));
			ps.setString(3, foodTruck.getFoodTruckDay());
			ps.setString(4, foodTruck.getFoodTruckTime());
			ps.setString(5,foodTruck.getFoodTruckLocation());
			ps.setInt(6, userId);
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			// pool.freeConnection(connection);
		}

	}

	/*
	 * Returns coordinates for any food truck.
	 */
	public static FoodTruck locateTruck(FoodTruck foodTruck) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT FROM FoodTruck (foodTruckId, foodTruckName)"
				+ "where  foodTruckId = ? and foodTruckName = ?";
		try {
			Connection connection = ConnectionPool2.getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, foodTruck.getFoodTruckId()); 
			ps.setString(2, foodTruck.getFoodTruckName());
			rs = ps.executeQuery();
			while(rs.next()) {
				foodTruck.setFoodTruckLocation(rs.getString("foodTruckLocation"));
				foodTruck.setLatitude(rs.getFloat("latitude"));
				foodTruck.setLongitude(rs.getFloat("longitude"));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return foodTruck;

	}
	
	public static ArrayList<FoodTruck> getAllLocations() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM FoodTruck WHERE foodTruckStatus = 'approve'";
		try {
			Connection connection = ConnectionPool2.getConnection();
			ps = connection.prepareStatement(query);
			ArrayList<FoodTruck> foodTruckList = new ArrayList<FoodTruck>();
			rs = ps.executeQuery();
			while(rs.next()) {
				FoodTruck foodTruck = new FoodTruck();
				foodTruck.setFoodTruckName(rs.getString("foodTruckName"));
				foodTruck.setFoodTruckLocation(rs.getString("truckLocation"));
				foodTruck.setFoodTruckId(rs.getInt("foodTruckId"));
				foodTruck.setLatitude(rs.getFloat("latitude"));
				foodTruck.setLongitude(rs.getFloat("longitude"));
				if(foodTruck.getLatitude() != 0.0){
					foodTruckList.add(foodTruck);
				}
			}
			return foodTruckList;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	

}

