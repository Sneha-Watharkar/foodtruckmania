package facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.LocationController;
import control.UserController;
import db.UserUpdate;

public class FoodTruckControllerFacade extends HttpServlet {

	private static final long serialVersionUID = 4624480356432936867L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/index.jsp";
		
		HttpSession session = request.getSession();
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

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
