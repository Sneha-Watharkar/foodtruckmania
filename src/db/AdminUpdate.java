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
		String query = "SELECT FROM FoodTruck WHERE foodTruckStatus='Pending'";
		try {
			//Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				FoodTruck foodTruck = new FoodTruck();
				foodTruck.setFoodTruckId(rs.getInt("foodtruckID"));
				foodTruck.setFoodTruckName(rs.getString("foodTruckName"));
				foodTruck.setFoodTruckStatus(rs.getString("foodTruckStatus"));
				list.add(foodTruck);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}
}
