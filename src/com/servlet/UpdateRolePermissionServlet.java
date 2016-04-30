package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RoleManagerDao;
import com.pojo.URolePe;
import com.pojo.UUserLogin;


public class UpdateRolePermissionServlet extends HttpServlet{

	/**
	 * 修改角色-权限信息
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
		String autoid = req.getParameter("autoid");
		String delautoid = req.getParameter("delautoid");
		UUserLogin user = (UUserLogin)req.getSession().getAttribute("userinfo");
		String mod_user = user.getUserId();
		
		try{
			String [] str1=autoid.split(",");
			String [] str2=delautoid.split(",");
			for(String str:str1){
				if(!"".equals(str)&&str!=null){
					URolePe bean = new URolePe();
					bean.setPermissionsId(str);
					bean.setRoleId(role_id);
					bean.setState("1");
					bean.setModUser(mod_user);
					new RoleManagerDao().insertRoleAuto(bean);
				}
			}
			for(String delstr:str2){
				if(!"".equals(delstr)&&delstr!=null){
					URolePe bean = new URolePe();
					bean.setPermissionsId(delstr);
					bean.setRoleId(role_id);
					new RoleManagerDao().deleteRoleAuto(bean);
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
