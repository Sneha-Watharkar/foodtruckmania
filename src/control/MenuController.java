package control;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import data.UserAccount;
import db.MenuUpdate;
import dbgateway.TableDataGateway;
import util.DBUtil;

@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
maxFileSize=1024*1024*50,      	// 50 MB
maxRequestSize=1024*1024*100)   	// 100 MB
public class MenuController extends HttpServlet{

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Came to face controller");
		String url = "/index.jsp";
        HttpSession session = request.getSession();
        UserAccount user = (UserAccount) session.getAttribute("user");
		String applicationPath = request.getServletContext().getRealPath("");
		File uploads = new File(applicationPath + "/uploads");
		if (!uploads.exists()) {
			uploads.mkdirs();
        }
		StringBuilder sb = new StringBuilder();
        ObjectMapper mapperObj = new ObjectMapper();
        JSONObject returnObj = new JSONObject();
		Part filePart = request.getPart("file");
		String fileName = getSubmittedFileName(filePart);
		filePart.write(applicationPath + "/uploads" + "/" + fileName);
		//File file = new File(uploads, fileName);
		int userId = user.getUserId();
		System.out.println(applicationPath + "/uploads" + "/" + fileName);
		int insertMenu = TableDataGateway.uploadMenu(userId,fileName);
		System.out.println(insertMenu);
		returnObj.put("msg", "File uploaded successfully");
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		pw.println(returnObj.toString());
		System.out.println(returnObj.toString());
	}
	
	private static String getSubmittedFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
	public static int uploadMenu(HttpServletRequest request, HttpServletResponse response, JSONObject data) {
		JSONObject truckJson = data.getJSONObject("truckDetails");
        File uploads = new File("/uploads");
		int userId = truckJson.getInt("userId");
		String filePath = truckJson.getString("filePath");
		Part filePart;
		try {
			filePart = request.getPart("file");
			String fileName = data.getString("name");
			File file = new File(uploads, fileName);
			try (InputStream input = filePart.getInputStream()) {
			    Files.copy(input, file.toPath());
				int insertMenu = TableDataGateway.uploadMenu(userId,fileName);
				return insertMenu;
			}
			} catch (IllegalStateException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public static String displayMenu(JSONObject data) {
		int foodTruckId = data.getInt("foodTruckId");
		return MenuUpdate.displayMenu(foodTruckId);
	}


}
