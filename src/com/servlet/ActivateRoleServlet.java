package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RoleManagerDao;

public class ActivateRoleServlet extends HttpServlet{

	/**
	 * 激活角色
	 */
	private static final long serialVersionUID = -7448099306743633721L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String role_id = req.getParameter("role_id");

		resp.setCharacterEncoding("UTF-8");  //设置字符集
		PrintWriter out = resp.getWriter();
		try{
			boolean flag = new RoleManagerDao().ActivateRole(role_id);
			if(flag){
				out.println("success");
			}else{
				out.println("false");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		out.flush();
		out.close();
	}

}
