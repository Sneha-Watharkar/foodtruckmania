package control;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import dbgateway.TableDataGateway;
import util.DBUtil;

public class MenuController {

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

	public static void displayMenu(JSONObject data) {
		

	}


}
