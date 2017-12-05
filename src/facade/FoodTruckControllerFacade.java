package facade;

import java.awt.MenuContainer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import control.AlertsController;
import control.LocationController;
import control.MenuController;
import control.RateController;
import control.UserController;
import data.FoodTruck;
import data.FoodTruckRating;
import data.UserAccount;
import db.AdminUpdate;
import db.UserUpdate;
import util.Mailer;

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
        HttpSession session = request.getSession();
        //ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        System.out.println("Request is "+sb.toString());
        JSONObject jObj =  new JSONObject(sb.toString());
		JSONObject data = jObj.getJSONObject("data");
		System.out.println("Data is " + data);
		String action = jObj.getString("action");
		try {
			System.out.println("Action is " + action);
			UserAccount userAcc = new UserAccount();
			switch (action) {
				case "registerUser":
					int registerSuccess = UserController.registerUser(request, response, data);
					if ( registerSuccess == 0){
						System.out.print("Register failed");
						returnObj.put("msg", "User Registration failed");
						returnObj.put("error", "None");
						returnObj.put("msg", "Register is successful");
					}
					else{
						returnObj.put("user", mapperObj.writeValueAsString(userAcc));
						System.out.print("Register is successful");
						returnObj.put("msg", "User Registration successful");
						returnObj.put("error", "None");
					}
					break;
				case "login":
					userAcc = UserController.login(request, response,data);
					if(userAcc != null){
						returnObj.put("user", mapperObj.writeValueAsString(userAcc));
						session.setAttribute("user", userAcc);
						if(userAcc.getUserType().equalsIgnoreCase("vendor")){
							System.out.println("User is Vendor");
							FoodTruck truck = UserUpdate.getFoodTrucks(userAcc.getTruckId());
							session.setAttribute("foodTruck", truck);
						}
						returnObj.put("msg", "Login Successful");
						returnObj.put("error", "");
					}
					else{
						//url = "/login.jsp";
						returnObj.put("msg", "Login Failed");
						returnObj.put("error", "UserName or Password is wrong");
					}
					break;
				case "getAllFoodTrucks":
					ArrayList<FoodTruck> foodTrucks = UserController.getAllFoodTrucks();
					returnObj.put("trucks", mapperObj.writeValueAsString(foodTrucks));
					break;
				case "fetchPendingApprovals":
					Map<Integer,String> results = UserController.fetchPendingApprovals();
					JSONArray jsonArray = new JSONArray();
					for (Map.Entry<Integer, String> entry : results.entrySet()) {
						JSONObject approval = new JSONObject();
						approval.put("foodtruckid",entry.getKey());
						approval.put("foodTruckName", entry.getValue());
						jsonArray.put(approval);
					}
					returnObj.put("results", jsonArray);
					returnObj.put("msg", "Returned "+results.values().size()+" records");
					break;
				case "approveFoodTrucks":
					int updateResult = UserController.approveFoodTruckRequests(request, response, data);
					if(updateResult == 1){
						returnObj.put("msg", "Approval Successful");
					}
					else{
						returnObj.put("msg", "Approval failed");
					}
					break;
				case "setAlerts":
					int success = AlertsController.insertAlerts(request, response, data);
					returnObj.put("msg", "Alerts inserted successfully");
					break;
				case "getAlerts":
					String alerts = AlertsController.getAlerts(request, response, data);
					System.out.println("Retrieved alerts: "+alerts);
					if(alerts != null && !alerts.isEmpty() ){	
						String[] alertArray = alerts.split("&");
						for(String s: alertArray){
							String[] alert = s.split("=");
							returnObj.put(alert[0],alert[1]);
						}
					}
					else{
						returnObj.put("text", 0);
						returnObj.put("email", 0);
					}
					break;
				case "reserveLocation":
					int locationUpdate = LocationController.reserveLocation(request, response, data);
					System.out.println(locationUpdate);
					if (locationUpdate == 1) {
						returnObj.put("msg", "Food Truck Location updated");
					}else {
						returnObj.put("msg", "Unable to save truck location");
					}
					break;
				case "locateTruck":
					FoodTruck foodTruck = LocationController.locateTruck(request, response, data);
					returnObj.put("msg", "Food Truck Location found");
					returnObj.put("latitude", foodTruck.getLatitude());
					returnObj.put("longitude", foodTruck.getLongitude());
					break;
				case "rating":
					int rateUpdate = RateController.rateTruck(request,response,data);
					if(rateUpdate == 1) {
						returnObj.put("msg", "Customer review complete");
					}else {
						returnObj.put("msg", "Unable to save customer review");
					}
					break;
				case "getFeedback":
					ArrayList<FoodTruckRating> ratingList = RateController.getFeedback(request, response, data);
					returnObj.put("ratingLists", mapperObj.writeValueAsString(ratingList));
					break;
				case "setUserFavorites":
					int favInsert = AlertsController.updateUserFav(request,response,data);
					if(favInsert == 1) {
						returnObj.put("msg", "Customer favorite updated");
					}else {
						returnObj.put("msg", "Unable to save customer favorite");
					}
					break;
				case "viewMenu":
					String fileName = MenuController.displayMenu(data);
					String applicationPath = request.getServletContext().getRealPath("");
					//response.setContentType("application/octet-stream");
				    response.setHeader("Content-Disposition", "filename="+fileName);
				    File srcFile = new File(applicationPath + "/uploads/"+fileName);
				    
				    OutputStream out = response.getOutputStream();
				    FileInputStream in = new FileInputStream(srcFile);
				    byte[] buffer = new byte[4096];
				    int length;
				    while ((length = in.read(buffer)) > 0){
				        out.write(buffer, 0, length);
				    }
				    response.setContentType(getServletContext().getMimeType(fileName));
				    in.close();
				    out.flush();
					break;
				case "sendMail":
					FoodTruck truck = (FoodTruck) session.getAttribute("foodTruck");
					ArrayList<String> emails = UserUpdate.getEmailAddress(truck.getFoodTruckId());
					for(String email:emails){
						Mailer.sendMail(email,"test@localhost.com","test","test",false);
					}
					break;
				case "getFavFoodTrucks":
					ArrayList<FoodTruck> favFoodTrucks = UserController.getFavFoodTrucks(request,response,data);
					returnObj.put("trucks", mapperObj.writeValueAsString(favFoodTrucks));
					break;
				case "getAllLocations":
					ArrayList<FoodTruck> foodTruckLocations = LocationController.getAllLocations(request,response,data);
					returnObj.put("trucks", mapperObj.writeValueAsString(foodTruckLocations));
					break;
				case "uploadMenu":
					if(MenuController.uploadMenu(request, response,data)==0) {
						returnObj.put("msg", "Menu Uploaded");
						returnObj.put("error", "None");
					}else {
						returnObj.put("msg", "Error uploading menu");
						returnObj.put("error", "Error");
					}
					break;
				case "displayMenu":
					MenuController.displayMenu(data);
					break;
				case "logout":
	                session.invalidate();
	                break;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		PrintWriter pw = response.getWriter();
		if(action != "viewMenu"){
			response.setContentType("application/json");
		}
		pw.println(returnObj.toString());
		System.out.println(returnObj.toString());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}

