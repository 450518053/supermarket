package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserMangerDao;
import com.google.gson.Gson;
import com.json.pojo.JsonData;
import com.pojo.UUserLogin;


/**   
 * @ClassName: UserShowServlet  
 * @Description:   显示用户列表
 * @author: marx.liao
 * @date 2016-4-29 上午10:06:44  
 *     
 */
public class UserShowServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");  //设置字符集
		PrintWriter out = resp.getWriter();
		String user_id = req.getParameter("user_id");
		String user_name = req.getParameter("user_name");
		String user_type = req.getParameter("user_type");
		int rows = Integer.parseInt(req.getParameter("rows"));
		int page = Integer.parseInt(req.getParameter("page"));
		String previous = req.getParameter("previous");
//		if ("true".equals(previous)) {
//			System.out.println("进入");
//			page = page - 1;
//		}
		JsonData json = new JsonData();
		json.setTotal(new UserMangerDao().getUserCount(user_id, user_name, user_type));
		json.setRows(new UserMangerDao().getUsers(user_id, user_name, user_type, rows, page));
		
		Gson gson = new Gson();
		out.println(gson.toJson(json));
		out.flush();
		out.close();
		
	}

}
