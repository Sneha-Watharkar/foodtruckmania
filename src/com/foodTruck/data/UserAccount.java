/**
 * 
 */
package com.foodTruck.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Durga
 *
 */
public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userFirstName;
	private String userLastName;
	private String userType;
	private String loginName;
	private String login_password;
	private Long userPhoneNumber;
	private String userEmailAddress;
	private String userAlertPreference;
	private ArrayList favTrucks;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLogin_password() {
		return login_password;
	}

	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}

	public Long getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(Long userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserEmailAddress() {
		return userEmailAddress;
	}

	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}

	public String getUserAlertPreference() {
		return userAlertPreference;
	}

	public void setUserAlertPreference(String userAlertPreference) {
		this.userAlertPreference = userAlertPreference;
	}

	public ArrayList getFavTrucks() {
		return favTrucks;
	}

	public void setFavTrucks(ArrayList favTrucks) {
		this.favTrucks = favTrucks;
	}

}
