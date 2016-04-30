package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserMangerDao;
import com.google.gson.Gson;
import com.json.pojo.Json;

/**   
 * @ClassName: UserIdVerify  
 * @Description:  userId校验 
 *     
 */
public class UserIdVerifyServlet extends HttpServlet{

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
		Json json = new Json();
		
		String user_id = req.getParameter("user_id");
		String id = new UserMangerDao().queryUserId(user_id);
		if (!"".equals(id)) {
			json.setSuccess(false);
			json.setMsg("error");
		} else {
			json.setSuccess(true);
			json.setMsg("ok");
		}
		out.println(new Gson().toJson(json));
		out.flush();
		out.close();
	}

}
