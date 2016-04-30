package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserMangerDao;

/**   
 * @ClassName: UserActivetyServlet  
 * @Description:   用户启用注销
 *     
 */
public class UserActivetyServlet extends HttpServlet{
	
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
		String state = req.getParameter("state");
		
		boolean flag = new UserMangerDao().userActivity(user_id, state);
		if(flag){
			out.println("success".trim());
		}else{
			out.println("error");
		}
		
		out.flush();
		out.close();
		
	}

}
