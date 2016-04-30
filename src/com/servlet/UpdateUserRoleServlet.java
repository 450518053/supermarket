package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserMangerDao;
import com.pojo.UUserLogin;
import com.pojo.UUserRole;

/**   
 * @ClassName: UpdateUserRole  
 * @Description:   修改用户-角色信息
 *     
 */
public class UpdateUserRoleServlet extends HttpServlet{
	
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
		String roleid = req.getParameter("roleid");
		String delroleid = req.getParameter("delroleid");
		HttpSession session = req.getSession();
		UUserLogin userBean = (UUserLogin) session.getAttribute("userinfo");
		String mod_user = "";
		if (userBean != null) {
			mod_user = userBean.getUserId();
		}
		
		try {
			String[] str1 = roleid.split(",");
			String[] str2 = delroleid.split(",");
			for (String str : str1) {
				if (!"".equals(str) && str != null) {
					UUserRole bean = new UUserRole();
					bean.setRoleId(str);
					bean.setUserId(user_id);
					bean.setState("1");
					bean.setModUser(mod_user);
					new UserMangerDao().insertUserRole(bean);
				}
			}
			for (String delstr : str2) {
				if (!"".equals(delstr) && delstr != null) {
					UUserRole bean = new UUserRole();
					bean.setRoleId(delstr);
					bean.setUserId(user_id);
					new UserMangerDao().deleteUserRole(bean);
				}
			}
			out.println("success".trim());
		} catch (Exception e) {
			out.println("error");
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
		
	}

}
