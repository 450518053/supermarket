package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserMangerDao;
import com.pojo.UUserLogin;
import com.pojo.UUserRole;

/**   
 * @ClassName: UserGetRoleServlet  
 * @Description:   获取用户-角色信息
 * @author: marx.liao
 * @date 2016-4-29 下午2:49:55  
 *     
 */
public class UserGetRoleServlet extends HttpServlet{
	
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
		List<UUserRole> haveRolelist = new ArrayList<UUserRole>();
		List<UUserRole> lackRolelist = new ArrayList<UUserRole>();
		haveRolelist = new UserMangerDao().haveRolelist(req.getParameter("user_id"));
		lackRolelist = new UserMangerDao().lackRolelist(req.getParameter("user_id"));
		req.getSession().setAttribute("haveRoleMap", haveRolelist);
		req.getSession().setAttribute("lackRoleMap", lackRolelist);
		req.getRequestDispatcher("../powerManage/HaveUser.jsp").forward(req,resp);
	}

}
