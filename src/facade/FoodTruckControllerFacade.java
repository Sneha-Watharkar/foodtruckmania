package facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonGenerationException;
import control.AlertsController;
import control.LocationController;
import control.RateController;
import control.UserController;
import data.FoodTruck;
import data.FoodTruckRating;
import data.UserAccount;
import db.AdminUpdate;
import db.UserUpdate;

public class FoodTruckControllerFacade extends HttpServlet {

	private static final long serialVersionUID = 4624480356432936867L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Came to face controller");
		String url = "/index.jsp";
		JSONObject returnObj = new JSONObject();
		StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        ObjectMapper mapperObj = new ObjectMapper();
        String str = null;
        //ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        System.out.println("Request is "+sb.toString());
        JSONObject jObj;
		try {
			jObj = new JSONObject(sb.toString());
			JSONObject data = jObj.getJSONObject("data");
			System.out.println("Data is " + data);
			String action = jObj.getString("action");
			System.out.println("Action is " + action);
			switch (action) {
				case "registerUser":
					if (UserController.registerUser(request, response,data) == 1){
						System.out.print("Register is successful");
						returnObj.put("msg", "User Registration successful");
						returnObj.put("error", "None");
					}
					else{
						System.out.print("Register failed");
						returnObj.put("msg", "User Registration failed");
						returnObj.put("error", "None");
					}
					break;
				case "login":
					UserAccount userAcc = UserController.login(request, response,data);
					if(userAcc != null){
						returnObj.put("user", mapperObj.writeValueAsString(userAcc));
						returnObj.put("msg", "Login Successful");
						returnObj.put("error", "None");
					}
					else{
						//url = "/login.jsp";
						request.setAttribute("msg", "Login Failed");
						request.setAttribute("error", "UserName or Password is wrong");
					}
					break;
				case "fetchPendingApprovals":
					Map<Integer,String> results = UserController.fetchPendingApprovals();
					request.setAttribute("results", results);
					request.setAttribute("msg", "Returned "+results.values().size()+" records");
					break;
				case "approveFoodTrucks":
					int updateResult = UserController.approveFoodTruckRequests(request, response, data);
					if(updateResult == 1){
						request.setAttribute("msg", "Approval Successful");
					}
					else{
						request.setAttribute("msg", "Approval failed");
					}
					break;
				case "setAlerts":
					int success = AlertsController.insertAlerts(request, response, data);
					request.setAttribute("msg", "Alerts inserted successfully");
					break;
				case "getAlerts":
					String alerts = AlertsController.getAlerts(request, response, data);
					request.setAttribute("alerts", alerts);
					break;
				case "reserveLocation":
					int locationUpdate = LocationController.reserveLocation(request, response, data);
					if(locationUpdate == 1) {
						request.setAttribute("msg", "Food Truck Location updated");
					}else {
						request.setAttribute("msg", "Unable to save truck location");
					}
					break;
				case "locateTruck":
					FoodTruck foodTruck = LocationController.locateTruck(request, response, data);
					request.setAttribute("msg", "Food Truck Location found");
					request.setAttribute("latitude", foodTruck.getLatitude());
					request.setAttribute("longitude", foodTruck.getLongitude());
					break;
				case "rateTruck":
					int rateUpdate = RateController.rateTruck(request,response,data);
					if(rateUpdate == 1) {
						request.setAttribute("msg", "Customer review complete");
					}else {
						request.setAttribute("msg", "Unable to save customer review");
					}
					break;
				case "getFeedback":
					ArrayList<FoodTruckRating> ratingList = RateController.getFeedback(request, response, data);
					request.setAttribute("ratingLists", ratingList);
					break;
				case "setUserFavorites":
					int favInsert = AlertsController.updateUserFav(request,response,data);
					if(favInsert == 1) {
						request.setAttribute("msg", "Customer favorite updated");
					}else {
						request.setAttribute("msg", "Unable to save customer favorite");
					}
					break;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		pw.println(returnObj.toString());
		//getServletContext().getRequestDispatcher().forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}

