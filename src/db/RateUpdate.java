package db;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import data.FoodTruck;
import data.FoodTruckRating;
import dbgateway.TableDataGateway;
import util.ConnectionPool2;
import util.DBUtil;

public class RateUpdate {
	
	/*
	 * DB Insert statement to insert the food truck rating into the FoodTruckRating table
	 */
	public static int rateTruck(int userId, FoodTruckRating rating) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		String query = "INSERT INTO FoodTruckRating (foodTruckId,userId,rating,ratingDate,Comment)" 
				+ "VALUES (?,?,?,?,?)";
		try {
//			Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, String.valueOf(rating.getFoodTruckId()));
			ps.setString(2, String.valueOf(userId));
			ps.setString(3, String.valueOf(rating.getRating()));
			ps.setTimestamp(4, (Timestamp) rating.getRatingDate());
			ps.setString(5, rating.getComments());
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			// pool.freeConnection(connection);
		}
	}
	public static ArrayList<FoodTruck> getFavFoodTrucks(int UserId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query1 = "SELECT * FROM UserFavorites where userID = ?";
		
		try{
			ArrayList<Integer> foodTruckList = new ArrayList<Integer>();
			Connection connection = ConnectionPool2.getConnection();
			ps = connection.prepareStatement(query1);
			ps.setInt(1, UserId);
			rs = ps.executeQuery();
			System.out.println("Result set is :" + rs.getRow());
			if(rs.next()){
				foodTruckList.add(rs.getInt("foodTruckID"));
			}
		}
		catch (Exception e) {
		System.out.println(e);
		}
		String query2 = "SELECT * from FoodTruck WHERE foodTruckID IN ?";
		try{
			ArrayList<FoodTruck> foodTruckList = new ArrayList<FoodTruck>();
			Connection connection = ConnectionPool2.getConnection();
			ps = connection.prepareStatement(query1);
			ps.setArray(1, (Array) foodTruckList);
			rs = ps.executeQuery();
			System.out.println("Result set is :" + rs.getRow());
			if(rs.next()){
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
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	/*
	 * DB statement to fetch all the food truck ratings.
	 */
	public static ArrayList<FoodTruckRating> getFeedback(int foodTruckId) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<FoodTruckRating> ratingsList = new ArrayList<>();
		
		String query = "Select * from FoodTruckRating" + "where foodTruckId = ?" ;
		try {
			Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			ps = connection.prepareStatement(query);
			ps.setString(1, String.valueOf(foodTruckId));
			
			rs = ps.executeQuery();
			
			FoodTruckRating foodTruckRating = null;
			if (rs.next()) {
				foodTruckRating = new FoodTruckRating();
				foodTruckRating.setRating(rs.getInt("foodTruckId"));
				foodTruckRating.setComments(rs.getString("Comment"));
				ratingsList.add(foodTruckRating);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBUtil.closePreparedStatement(ps);
			// pool.freeConnection(connection);
		}
		return ratingsList;
	}

}
