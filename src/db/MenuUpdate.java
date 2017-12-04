package db;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import dbgateway.TableDataGateway;
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

}
