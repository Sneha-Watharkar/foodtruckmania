package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import dbgateway.TableDataGateway;
import util.ConnectionPool2;
import util.DBUtil;

public class AlertsUpdate {
	
	/*
	 * Insert statement to update user favorites.UserFavorites table is affected.
	 */
	public static int updateUserFavorite(int userId, int foodTruckId) {
		/*
		 * ConnectionPool pool = ConnectionPool.getInstance(); Connection connection =
		 * pool.getConnection();
		 */
		PreparedStatement ps = null;
		String query = "INSERT INTO UserFavorites (userId,foodTruckId)" + "VALUES (?,?)";
		try {
//			Connection connection = DriverManager.getConnection(TableDataGateway.connectionString);
			Connection connection = ConnectionPool2.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, String.valueOf(userId));
			ps.setString(2, String.valueOf(foodTruckId));
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
