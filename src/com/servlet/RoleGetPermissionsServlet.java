package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RoleManagerDao;
import com.pojo.URolePe;

/**   
 * @ClassName: UserGetRoleServlet  
 * @Description:   获取角色-权限信息
 *     
 */
public class RoleGetPermissionsServlet extends HttpServlet{
	
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
		List<URolePe> haveRolelist = new ArrayList<URolePe>();
		List<URolePe> lackRolelist = new ArrayList<URolePe>();
		haveRolelist = new RoleManagerDao().havePermissionlist(req.getParameter("role_id"));
		lackRolelist = new RoleManagerDao().lackPermissionlist(req.getParameter("role_id"));
		req.getSession().setAttribute("haveAutoMap", haveRolelist);
		req.getSession().setAttribute("lackAutoMap", lackRolelist);
		req.getRequestDispatcher("../powerManage/HaveAuto.jsp").forward(req,resp);
	}

}
