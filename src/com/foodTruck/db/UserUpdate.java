package com.foodTruck.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.foodTruck.data.FoodTruck;
import com.foodTruck.data.UserAccount;

import com.foodTruck.util.ConnectionPool;

import com.foodTruck.util.DBUtil;

public class UserUpdate {

	public static int registerUser(UserAccount userAcc) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "INSERT INTO UserAccount (userFirstName, userLastName, userType,loginName,password,userPhoneNumber,userEmailAddress) "
				+ "VALUES (?, ?, ?,?,?,?,?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, userAcc.getUserFirstName());
			ps.setString(2, userAcc.getUserLastName());
			ps.setString(3, userAcc.getUserType());
			ps.setString(4, userAcc.getLoginName());
			ps.setString(5, userAcc.getLogin_password());
			ps.setString(6, String.valueOf(userAcc.getUserPhoneNumber()));
			ps.setString(7, userAcc.getUserEmailAddress());
			return ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}

	}
	
	public static UserAccount selectUserForLogin(String loginName,String password,String userType) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT FROM UserAccount"
				+ "where loginName = ? and password = ? and userType = ?";
		try {
			ps = connection.prepareStatement(query);
            ps.setString(1, loginName);
            ps.setString(2, password);
            ps.setString(3, userType);
            rs = ps.executeQuery();
            UserAccount userAcc = null;
            if(rs.next()) {
            	userAcc = new UserAccount();
            	userAcc.setUserId(rs.getInt("userId"));
            	userAcc.setUserFirstName(rs.getString("userFirstName"));
            	userAcc.setUserLastName(rs.getString("userLastName"));
            	userAcc.setUserType(rs.getString("userType"));
            	userAcc.setLoginName(rs.getString("loginName"));
            	userAcc.setLogin_password(rs.getString("password"));
            	userAcc.setUserPhoneNumber(Long.parseLong((rs.getString("userPhoneNumber"))));
            	userAcc.setUserEmailAddress(rs.getString("emailAddress"));
            }
            return userAcc;
		}catch(SQLException e) {
			System.out.println(e);
            return null;
		}finally {
			DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
		}
	}
	
	//Made it hashmap instead of arraylist. For each userIdinsert the food truck info.
	public static int approveVendorRequests(ArrayList vendorLists) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "UPDATE FoodTruck set foodTruckStatus=?"+ "where foodTruck_id=?";
		/*
		 * To add code to iterate the foodtruckstatus to active
		 * for(FoodTruck fdTruck:vendorLists) {
			fdTruck = new FoodTruck();
		}*/ 
		
		return 0;
		
	}
}