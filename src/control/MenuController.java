package control;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dbgateway.TableDataGateway;

public class MenuController {

	public static int uploadMenu(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		JSONObject truckJson = data.getJSONObject("truckDetails");
		int userId = truckJson.getInt("userId");
		String filePath = truckJson.getString("filePath");
		int insertMenu = TableDataGateway.uploadMenu(userId,filePath);
		return insertMenu;
	}

	public static void displayMenu(JSONObject data) {
		

	}


}
