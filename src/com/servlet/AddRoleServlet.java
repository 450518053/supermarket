package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RoleManagerDao;
import com.pojo.URole;


public class AddRoleServlet extends HttpServlet{

	/**
	 * 添加角色
	 */
	private static final long serialVersionUID = 1036059539116560048L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String role_id=req.getParameter("add_role_id");
		String role_name=req.getParameter("add_role_name");
		
		resp.setCharacterEncoding("UTF-8");  //设置字符集
		PrintWriter out = resp.getWriter();
		try{
			URole role = new RoleManagerDao().queryRoleById(role_id);
			if(role.getRoleId() ==null || role.getRoleId().equals("")){
				boolean flag = new RoleManagerDao().addRole(role_id, role_name);
				if(flag){
					out.println("success");
				}else{
					out.println("false");
				}
			}else{
				out.println("error");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
		
		
	}

}
