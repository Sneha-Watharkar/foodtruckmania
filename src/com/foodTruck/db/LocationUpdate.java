package com.foodTruck.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.foodTruck.data.FoodTruck;
import com.foodTruck.db.gateway.TableDataGateway;
import com.foodTruck.util.DBUtil;

public class LocationUpdate {

	public static int reserveLocation(String userId, FoodTruck foodTruck) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		String query = "INSERT INTO FoodTruck (userId, foodTruckName, latitude, longitude, truckTime, foodTruckStatus) "
				+ "VALUES (?,?,?,?,?,?)";
		try {
			Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setString(1, userId);
			ps.setString(2, foodTruck.getFoodTruckName());
			ps.setString(3, String.valueOf(foodTruck.getLatitude()));
			ps.setString(4, String.valueOf(foodTruck.getLongitude()));
			// ps.setString(5, foodTruck.getFoodTruckTime());
			ps.setString(6, foodTruck.getFoodTruckStatus());
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			// pool.freeConnection(connection);
		}

	}

}
