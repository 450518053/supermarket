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
 * @ClassName: UserRegisterServlet  
 * @Description:  用户注册 
 *     
 */
public class UserRegisterServlet extends HttpServlet{

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
		UUserLogin bean = new UUserLogin();
		bean.setUserId(req.getParameter("user_id").toLowerCase());
		bean.setUserPwd(DigestUtils.md5Hex(req.getParameter("user_pwd")));
		bean.setUserName(req.getParameter("user_name"));
		bean.setState("1");
		bean.setIsLogin("0");
		bean.setUserType(req.getParameter("user_type"));
		bean.setDept(req.getParameter("dept"));
		String id = new UserMangerDao().queryUserId(req.getParameter("user_id"));
		
		if (id != null && !"".equals(id)) {
			json.setMessage("用户ID重复");
		} else {
			boolean flag = new UserMangerDao().register(bean);
			if (flag) {
				json.setMessage("ok");
			} else {
				json.setMessage("error");
			}
		}
		out.println(new Gson().toJson(json));
		out.flush();
		out.close();
	}

}
