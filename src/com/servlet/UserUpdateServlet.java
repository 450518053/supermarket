package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserMangerDao;

/**   
 * @ClassName: UserUpdateServlet  
 * @Description:   用户修改
 * @author: marx.liao
 * @date 2016-4-29 下午1:47:06  
 *     
 */
public class UserUpdateServlet extends HttpServlet{

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
		String dept = req.getParameter("dept");
		
		boolean flag = new UserMangerDao().updateUser(user_id, user_name, dept);
		if(flag){
			out.println("success".trim());
		}else{
			out.println("error");
		}
		
		out.flush();
		out.close();
		
	}

}
