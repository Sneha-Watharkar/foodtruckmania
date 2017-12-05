package db;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbgateway.TableDataGateway;
import util.ConnectionPool2;
import util.DBUtil;

public class MenuUpdate {

	public static int uploadMenu(int userId, String filePath) {
		PreparedStatement ps = null;
		String query = "UPDATE FoodTruck SET foodTruckMenu=? WHERE userId = ?";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setString(1, filePath);
			ps.setInt(2, userId);
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}
	
	public static String displayMenu(int userId) {
		PreparedStatement ps = null;
		String query = "SELECT foodTruckMenu FROM FoodTruck " +  "where userId = ?";
		String filePath;
		try {
			Connection connection = ConnectionPool2.getConnection();
			System.out.println(connection.toString());
			ps = connection.prepareStatement(query);
			System.out.println(ps.toString());
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				filePath = rs.getString("foodTruckMenu");
				return filePath;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closePreparedStatement(ps);
		}
	}

}
