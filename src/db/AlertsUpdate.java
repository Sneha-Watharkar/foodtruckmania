package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbgateway.TableDataGateway;
import util.ConnectionPool2;
import util.DBUtil;

public class AlertsUpdate {
	
	/*
	 * Insert statement to update user favorites.UserFavorites table is affected.
	 */
	public static int insertAlerts(int userId, String Alert) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		String query = "SET userAlertPreference = ? FROM UserAccount WHERE userId = ?";
		try {
//			Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, String.valueOf(userId));
			ps.setString(2, String.valueOf(Alert));
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally { 
			DBUtil.closePreparedStatement(ps);
			// pool.freeConnection(connection);
		}

	}
	public static int getUserAlerts(int userId, String alerts) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SET userAlertPreference = ? FROM UserAccount WHERE userId = ?";
		try {
//			Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, alerts);
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
	

}
