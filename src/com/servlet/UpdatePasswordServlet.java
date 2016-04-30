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
 * @ClassName: UpdatePasswordServlet  
 * @Description:   修改密码
 *     
 */
public class UpdatePasswordServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5189267621068764519L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");  //设置字符集
		PrintWriter out = resp.getWriter();
		JSONResult json = new JSONResult();
		try {
			String user_id = req.getParameter("user_id");
			String oldpwd = req.getParameter("oldpwd");
			String newpwd = req.getParameter("newpwd");
			UUserLogin bean = new UserMangerDao().selectUserById(user_id);
			if (bean == null) {
				bean = new UUserLogin();
			}
			if (!bean.getUserPwd().equals(DigestUtils.md5Hex(oldpwd))) {
				json.setMessage("你输入的原始密码不正确！");

			} else {
				new UserMangerDao().restPassword(user_id, DigestUtils.md5Hex(newpwd));
				json.setMessage("修改密码成功");
			}
			out.println(new Gson().toJson(json));
			out.flush();
			out.close();
		}catch (Exception e) {
			json.setMessage("系统异常!");
			e.printStackTrace();
		}
	}

}
