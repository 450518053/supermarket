package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserMangerDao;
import com.pojo.UUserLogin;

/**   
 * @ClassName: UserSelectByIdServlet  
 * @Description:   通过id查询用户
 * @author: marx.liao
 * @date 2016-4-29 下午12:59:31  
 *     
 */
public class UserSelectByIdServlet extends HttpServlet{

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
		UUserLogin bean = new UUserLogin();
		bean = new UserMangerDao().selectUserById(req.getParameter("user_id"));
		req.getSession().setAttribute("bean", bean);
		req.getRequestDispatcher("../powerManage/UpdateUser.jsp").forward(req,resp);
	}

}
