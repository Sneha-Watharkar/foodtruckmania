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
			ps.setDate(4, new java.sql.Date(rating.getRatingDate().getTime()));
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
	public static int setFavFoodTrucks(int userId, int foodTruckId){
		PreparedStatement ps = null;
		String query = "INSERT INTO UserFavorites(foodTruckId,userId)" 
				+ "VALUES (?,?)";
		try {
//			Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, foodTruckId);
			ps.setInt(2, userId);
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
		System.out.println("Usr id"+UserId);
			try{
				ArrayList<FoodTruck> foodTruckList = new ArrayList<FoodTruck>();
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				String query2 = "SELECT * from FoodTruck WHERE foodTruckId IN (SELECT foodTruckId FROM UserFavorites where userId = ?)";
				Connection connection = ConnectionPool2.getConnection();
				ps1 = connection.prepareStatement(query2);
				ps1.setInt(1, UserId);
				rs1 = ps1.executeQuery();
				System.out.println("Result set is :" + rs1.getRow());
				while(rs1.next()){
					FoodTruck truck = new FoodTruck();
					truck.setFoodTruckId(rs1.getInt("foodTruckId"));
					truck.setFoodTruckName(rs1.getString("foodTruckName"));
					truck.setLatitude(rs1.getFloat("latitude"));
					truck.setLongitude(rs1.getFloat("longitude"));
					truck.setFoodTruckMenu(rs1.getString("foodTruckMenu"));
					truck.setFoodTruckStatus(rs1.getString("foodTruckStatus"));
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
	public static ArrayList<FoodTruckRating> getFeedback(int userId) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<FoodTruckRating> ratingsList = new ArrayList<>();
		
		String query = "SELECT * from FoodTruckRating WHERE foodTruckId = (SELECT foodTruckID from foodTruck WHERE UserId = ?)" ;
		try {
			Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			ps = connection.prepareStatement(query);
			ps.setString(1, String.valueOf(userId));
			
			rs = ps.executeQuery();
			System.out.println(userId);
			while (rs.next()) {
				FoodTruckRating foodTruckRating = new FoodTruckRating();
				foodTruckRating.setRating(rs.getInt("rating"));
				foodTruckRating.setRatingDate(rs.getDate("ratingDate"));
				foodTruckRating.setFoodTruckId(rs.getInt("foodTruckId"));
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
