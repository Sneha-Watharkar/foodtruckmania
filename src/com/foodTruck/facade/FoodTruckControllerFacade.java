package com.foodTruck.facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodTruck.control.UserController;

public class FoodTruckControllerFacade extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/index.jsp";

		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		switch (action) {
		case "register":
			UserController.registerUser(request,response);
			break;
		case "signUp":
			UserController.signUpUser(request,response);
			break;
		case "reserve_location":
			//after logging in if user is vendor he/she chooses the location and time of food truck.
			//data enters the FoodTruck DB.
			break;
		case "approveVendorRequest":
			//once vendor chooses time and location admin approves the request.
			break;
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
