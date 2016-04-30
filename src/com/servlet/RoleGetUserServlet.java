package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RoleManagerDao;
import com.pojo.UUserRole;

public class RoleGetUserServlet extends HttpServlet{

	/**
	 * 获取角色-用户信息
	 */
	private static final long serialVersionUID = -7333274790753848940L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<UUserRole> haveRolelist = new ArrayList<UUserRole>();
		List<UUserRole> lackRolelist = new ArrayList<UUserRole>();
		haveRolelist = new RoleManagerDao().haveRole(req.getParameter("role_id"));
		lackRolelist = new RoleManagerDao().lackRole(req.getParameter("role_id"));
		req.getSession().setAttribute("haveMap", haveRolelist);
		req.getSession().setAttribute("lackMap", lackRolelist);
		req.getRequestDispatcher("../powerManage/Haverole.jsp").forward(req,resp);
	}

}
