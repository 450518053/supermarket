package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.dao.UserMangerDao;
import com.google.gson.Gson;
import com.json.pojo.Json;
import com.pojo.UPermissions;
import com.pojo.UUserLogin;
import com.util.CommonUtil;

/**   
 * @ClassName: UserLoginServlet  
 * @Description:   
 *     
 */
public class UserLoginServlet extends HttpServlet{
	
	public static final long serialVersionUID = 21560L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");  //设置字符集
		String loginname = req.getParameter("loginname");
		String password = req.getParameter("password");
		
		Json json = new Json();
		json.setSuccess(false);
		PrintWriter out = resp.getWriter();
		
		UUserLogin user =  new UserMangerDao().getUserLoign(loginname);
		if(user.getUserId() != null){
			if("1".equals(user.getState())){
				if(DigestUtils.md5Hex(password).trim().equals(user.getUserPwd())){
					if(!"1".equals(user.getIsLogin()) || CommonUtil.getIpAddr(req).equals(user.getLastLoginIp())){
						json.setSuccess(true);
						//修改登陆状态 和 登陆ip
						new UserMangerDao().updateStateAndIP(user.getUserId(), "1", CommonUtil.getIpAddr(req));
						
						//得到用户对应角色的权限集合
						List<UPermissions> list = new UserMangerDao().getUserAutoList(loginname);
						String user_permissions = "";
						if (list != null && list.size() > 0) {
							for (int i = 0; i < list.size(); i++) {
								UPermissions upb = (UPermissions) list.get(i);
								user_permissions = user_permissions + upb.getPermissionsId() + ",";
							}
						}
						if (user_permissions.length() > 0) {
							user_permissions = user_permissions.substring(0, user_permissions.length() - 1);
						}
						req.getSession().setAttribute("user_permissions", user_permissions); //权限集合存入session中
						req.getSession().setAttribute("userinfo", user);
						
					}else{
						json.setMsg("该用户已登陆!IP:" + user.getLastLoginIp());
					}
				}else{
					json.setMsg("密码错误!");
				}
			}else{
				json.setMsg("用户已注销,请联系管理员!");
			}
		}else{
			json.setMsg("用户不存在!");
		}
		
		out.println(new Gson().toJson(json));
		out.flush();
		out.close();
		
	}

}
