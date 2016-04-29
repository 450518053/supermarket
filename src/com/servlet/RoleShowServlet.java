package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RoleManagerDao;
import com.dao.UserMangerDao;
import com.google.gson.Gson;
import com.json.pojo.JsonData;

/**   
 * @ClassName: RoleShowServlet  
 * @Description:   显示角色列表
 * @author: marx.liao
 * @date 2016-4-29 下午4:27:15  
 *     
 */
public class RoleShowServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7253345622973512485L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");  //设置字符集
		PrintWriter out = resp.getWriter();
		String role_id = req.getParameter("user_id");
		String role_name = req.getParameter("user_name");
		int rows = Integer.parseInt(req.getParameter("rows"));
		int page = Integer.parseInt(req.getParameter("page"));
//		String previous = req.getParameter("previous");
//		if ("true".equals(previous)) {
//			System.out.println("进入");
//			page = page - 1;
//		}
		JsonData json = new JsonData();
		json.setTotal(new RoleManagerDao().getRoleCount(role_id, role_name));
		json.setRows(new RoleManagerDao().getRoles(role_id, role_name, rows, page));
		
		Gson gson = new Gson();
		out.println(gson.toJson(json));
		out.flush();
		out.close();
		
	}

}
