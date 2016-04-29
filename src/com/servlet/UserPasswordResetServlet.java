package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.dao.UserMangerDao;
import com.google.gson.Gson;
import com.json.pojo.JSONResult;
import com.pojo.UUserLogin;

/**   
 * @ClassName: UserPasswordReset  
 * @Description:  重置密码 
 * @author: marx.liao
 * @date 2016-4-29 下午2:13:35  
 *     
 */
public class UserPasswordResetServlet extends HttpServlet{

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
		
		JSONResult json = new JSONResult();
		
		boolean flag = new UserMangerDao().restPassword(req.getParameter("user_id"),DigestUtils.md5Hex("123456"));
		
		if (flag) {
			json.setMessage("重置密码成功,初始密码为123456");
		} else {
			json.setMessage("重置异常，请联系管理员!");
		}
		out.println(new Gson().toJson(json));
		out.flush();
		out.close();
	}

}
