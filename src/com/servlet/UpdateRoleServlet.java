package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RoleManagerDao;

public class UpdateRoleServlet extends HttpServlet{

	/**
	 * 修改角色信息
	 */
	private static final long serialVersionUID = 7914192462549544220L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String role_id = req.getParameter("upd_role_id");
		String role_name = req.getParameter("upd_role_name");

		resp.setCharacterEncoding("UTF-8");  //设置字符集
		PrintWriter out = resp.getWriter();
		try{
			boolean flag = new RoleManagerDao().updateRole(role_id, role_name);
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
