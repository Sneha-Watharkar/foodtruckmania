package facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import control.LocationController;
import control.UserController;
import db.UserUpdate;

public class FoodTruckControllerFacade extends HttpServlet {

	private static final long serialVersionUID = 4624480356432936867L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Came to face controller");
		String url = "/index.jsp";
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        
        System.out.println("Request is "+sb.toString());
        JSONObject jObj = new JSONObject(sb.toString());
        JSONObject name = jObj.getJSONObject("data");
        System.out.println("Data is "+name);
        
        /*JsonObject js = gson.toJson(sb.toString());*/
        /*String name = jObj.getString("name");
 
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("hello from java. you entered : " + name);*/
        /*JsonParser parser = new JsonParser();
        BufferedReader s = request.getReader();
        
        JsonObject obj = (JsonObject) parser
                .parse(request.getReader());*/
        /*System.out.println("Object is"+obj);*/
		
		/*HttpSession session = request.getSession();
		String action = request.getParameter("action");
		System.out.println("Action is : " + action);
		switch (action) {
		case "registerUser":
			UserController.registerUser(request, response);
			break;
		case "signUp":
			UserController.signUpUser(request, response);
			break;
		case "reserveLocation":
			// after logging in if user is vendor he/she chooses the location and time of
			// food truck.
			// data enters the FoodTruck DB.
			LocationController.reserveLocation(request, response);
			break;
		case "approveVendorRequest":
			// once vendor chooses time and location admin approves the request.
//			UserUpdate.approveVendorRequests(request,response)
			break;
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);*/
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
