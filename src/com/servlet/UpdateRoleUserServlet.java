package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserMangerDao;
import com.pojo.UUserRole;
import com.pojo.UUserLogin;


public class UpdateRoleUserServlet extends HttpServlet{

	/**
	 * 修改角色-用户信息
	 */
	private static final long serialVersionUID = 5350575505242413102L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");  //设置字符集
		PrintWriter out = resp.getWriter();
		
		String role_id = req.getParameter("role_id");
		String userid = req.getParameter("userid");
		String deluserid = req.getParameter("deluserid");
		UUserLogin user = (UUserLogin)req.getSession().getAttribute("userinfo");
		String mod_user = user.getUserId();
		
		try{
			String [] str1=userid.split(",");
			String [] str2=deluserid.split(",");
			for(String str:str1){
				if(!"".equals(str)&&str!=null){
					UUserRole bean = new UUserRole();
					bean.setUserId(str);
					bean.setRoleId(role_id);
					bean.setState("1");
					bean.setModUser(mod_user);
					new UserMangerDao().insertUserRole(bean);
				}
			}
			for(String delstr:str2){
				if(!"".equals(delstr)&&delstr!=null){
					UUserRole bean = new UUserRole();
					bean.setUserId(delstr);
					bean.setRoleId(role_id);
					new UserMangerDao().deleteUserRole(bean);
				}
			}
			out.println("success");
			
		}catch(Exception e){
			out.println("error");
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

}
