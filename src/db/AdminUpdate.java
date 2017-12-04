package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import data.FoodTruck;
import util.ConnectionPool2;
import util.DBUtil;

public class AdminUpdate {
	public static ArrayList<FoodTruck> fetchPendingApprovals(){
		ArrayList<FoodTruck> list = new ArrayList<FoodTruck>();
		PreparedStatement ps = null;
		String query = "SELECT * FROM FoodTruck WHERE foodTruckStatus='Pending'";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			System.out.println("length of the lsit: ");
			while (rs.next()) {
				FoodTruck foodTruck = new FoodTruck();
				foodTruck.setFoodTruckId(rs.getInt("foodtruckID"));
				foodTruck.setFoodTruckName(rs.getString("foodTruckName"));
				foodTruck.setFoodTruckStatus(rs.getString("foodTruckStatus"));
				System.out.println(rs.getString("foodTruckName"));
				list.add(foodTruck);
			}
			System.out.println("length of the lsit: "+list.size());
			return list;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}
	public static int updateFoodTruckStatus(int foodTruckId, String foodTruckStatus){
		PreparedStatement ps = null;
		String query = "UPDATE FoodTruck SET foodTruckStatus = ? WHERE foodTruckId=?";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setString(1,foodTruckStatus);
			ps.setInt(2, foodTruckId);
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}
}
