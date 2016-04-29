package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pojo.UPermissions;
import com.pojo.UUserLogin;
import com.pojo.UUserRole;
import com.util.CommonUtil;
import com.util.ConnectionUtil;

/**   
 * @ClassName: UserMangerDao  
 * @Description:  用户管理Dao 
 * @author: marx.liao
 * @date 2016-4-28 上午10:38:55  
 *     
 */
public class UserMangerDao {
	/**
	 * 
	 * @Method: getUserLoign    
	 * @Description: 用户登陆
	 * @author: marx.liao
	 * @date 2016-4-28 下午2:05:45
	 * @param loginname
	 * @return       
	 * @throws
	 */
	public UUserLogin getUserLoign(String loginname){
		UUserLogin user = new UUserLogin();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select *  from u_user_login where user_id = ? ";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginname);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPwd(rs.getString("user_pwd"));
				user.setLastLoginIp(rs.getString("last_login_ip"));
				user.setLastLoginTime(rs.getString("last_login_time"));
				user.setState(rs.getString("state"));
				user.setDept(rs.getString("dept"));
				user.setIsLogin(rs.getString("is_login"));
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return user;
	}
	
	/**
	 * 
	 * @Method: updateStateAndIP    
	 * @Description: 修改登陆状态，登陆时间，登陆ip
	 * @author: marx.liao
	 * @date 2016-4-28 下午2:06:07
	 * @param userId
	 * @param login
	 * @param ip       
	 * @throws
	 */
	public void updateStateAndIP(String userId,String login,String ip){
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "update  u_user_login set is_login = ?,last_login_ip = ?,last_login_time = ? where user_id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login);
			pstmt.setString(2, ip);
			pstmt.setString(3, CommonUtil.getNowDate());
			pstmt.setString(4, userId);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Method: getUserAutoList    
	 * @Description: 获取权限
	 * @author: marx.liao
	 * @date 2016-4-28 下午2:14:35
	 * @param name
	 * @return       
	 * @throws
	 */
	public List<UPermissions> getUserAutoList(String name){
		List<UPermissions> list = new ArrayList<UPermissions>();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "";
		if(name.equals("admin")){
			sql = "select permissions_id  from u_permissions where state='1'";
		}else{
			sql = "select c.permissions_id permissionsId from u_role_pe c, u_permissions d where c.permissions_id=d.permissions_id and d.state='1' and c.role_id in (select a.role_id from u_user_role a,u_role b where a.role_id=b.role_id and user_id='"+name+"' and b.state='1')";
		}
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				UPermissions uPermissions = new UPermissions();
				uPermissions.setPermissionsId(rs.getString("permissions_id"));
				list.add(uPermissions);
			}
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @Method: queryUserId    
	 * @Description: 查询id是否重复
	 * @author: marx.liao
	 * @date 2016-4-28 下午5:27:06
	 * @param userId
	 * @return       
	 * @throws
	 */
	public String queryUserId(String userId){
		String id = "";
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select user_id from u_user_login where user_id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("user_id");
			}
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * 
	 * @Method: register    
	 * @Description: 注册用户
	 * @author: marx.liao
	 * @date 2016-4-28 下午5:42:05
	 * @param bean
	 * @return       
	 * @throws
	 */
	public boolean register(UUserLogin bean){
		boolean flag = false;
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "insert into u_user_login (user_id,user_name,user_pwd,user_type,is_login,state,dept) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUserId());
			pstmt.setString(2, bean.getUserName());
			pstmt.setString(3, bean.getUserPwd());
			pstmt.setString(4, bean.getUserType());
			pstmt.setString(5, bean.getIsLogin());
			pstmt.setString(6, bean.getState());
			pstmt.setString(7, bean.getDept());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * @Method: getUserCount    
	 * @Description: 获得人员列表总数
	 * @author: marx.liao
	 * @date 2016-4-29 上午10:45:04
	 * @param user_id
	 * @param user_name
	 * @param user_type
	 * @return       
	 * @throws
	 */
	public int getUserCount(String user_id,String user_name,String user_type){
		int count = 0;
		
		Connection con = ConnectionUtil.getDBConnection();
		StringBuffer sql = new StringBuffer("select count(1) as allcount from u_user_login where 1=1");
		if(user_id != null && !user_id.equals("")){
			sql.append(" and user_id like '%"+user_id+"%'" );
		}
		if(user_name != null && !user_name.equals("")){
			sql.append(" and user_name like '%"+user_name+"%'" );
		}
		if(user_type != null && !user_type.equals("")){
			sql.append(" and user_type like '%"+user_type+"%'") ;
		}
		sql.append(" and user_id != 'admin' ");
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("allcount");
			}
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	/**
	 * 
	 * @Method: getUsers    
	 * @Description: 获得用户列表数据
	 * @author: marx.liao
	 * @date 2016-4-29 下午1:09:50
	 * @param user_id
	 * @param user_name
	 * @param user_type
	 * @param rows
	 * @param page
	 * @return       
	 * @throws
	 */
	public List<UUserLogin> getUsers(String user_id,String user_name,String user_type,int rows,int page){
		List<UUserLogin> list = new ArrayList<UUserLogin>();
		Connection con = ConnectionUtil.getDBConnection();
		StringBuffer sql = new StringBuffer("select * from u_user_login where 1=1");
		int startRow = (page - 1) * rows;
		if(user_id != null && !user_id.equals("")){
			sql.append(" and user_id like '%"+user_id+"%'");
		}
		if(user_name != null && !user_name.equals("")){
			sql.append(" and user_name like '%"+user_name+"%'");
		}
		if(user_type != null && !user_type.equals("")){
			sql.append(" and user_type like '%"+user_type+"%'");
		}
		sql.append(" and user_id != 'admin' ");
		sql.append(" limit ?,?");
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, rows);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UUserLogin user = new UUserLogin();
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPwd(rs.getString("user_pwd"));
				user.setLastLoginIp(rs.getString("last_login_ip"));
				user.setLastLoginTime(rs.getString("last_login_time"));
				user.setState(rs.getString("state"));
				user.setDept(rs.getString("dept"));
				user.setIsLogin(rs.getString("is_login"));
				user.setUserType(rs.getString("user_type"));
				user.setUnregister(rs.getString("unregister"));
				user.setUnregistTime(rs.getString("unregist_time"));
				list.add(user);
			}
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @Method: selectUserById    
	 * @Description: 用过id获取用户资料
	 * @author: marx.liao
	 * @date 2016-4-29 下午1:50:07
	 * @param user_id
	 * @return       
	 * @throws
	 */
	public UUserLogin selectUserById(String user_id){
		UUserLogin user = new UUserLogin();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select * from u_user_login where user_id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPwd(rs.getString("user_pwd"));
				user.setLastLoginIp(rs.getString("last_login_ip"));
				user.setLastLoginTime(rs.getString("last_login_time"));
				user.setState(rs.getString("state"));
				user.setDept(rs.getString("dept"));
				user.setIsLogin(rs.getString("is_login"));
				user.setUserType(rs.getString("user_type"));
			}
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 
	 * @Method: updateUser    
	 * @Description: 修改用户
	 * @author: marx.liao
	 * @date 2016-4-29 下午2:20:00
	 * @param user_id
	 * @param user_name
	 * @param dept
	 * @return       
	 * @throws
	 */
	public boolean updateUser(String user_id,String user_name,String dept){
		boolean flag = false;
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "update  u_user_login set user_name = ?,dept = ? where user_id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, dept);
			pstmt.setString(3, user_id);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		flag = true;
		
		return flag;
		
	}
	
	/**
	 * 
	 * @Method: restPassword    
	 * @Description: 重置密码
	 * @author: marx.liao
	 * @date 2016-4-29 下午2:21:18
	 * @param user_id
	 * @param password
	 * @return       
	 * @throws
	 */
	public boolean restPassword(String user_id,String password){
		boolean flag = false;
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "update  u_user_login set user_pwd = ? where user_id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, user_id);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		flag = true;
		
		return flag;
	}
	
	/**
	 * 
	 * @Method: restPassword    
	 * @Description: 重置密码
	 * @author: marx.liao
	 * @date 2016-4-29 下午2:21:18
	 * @param user_id
	 * @param password
	 * @return       
	 * @throws
	 */
	public boolean userActivity(String user_id,String state){
		boolean flag = false;
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "update  u_user_login set state = ? where user_id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, state);
			pstmt.setString(2, user_id);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		flag = true;
		
		return flag;
	}
	
	/**
	 * 
	 * @Method: haveRolelist    
	 * @Description: 获取用户拥有的角色
	 * @author: marx.liao
	 * @date 2016-4-29 下午3:27:39
	 * @param user_id
	 * @return       
	 * @throws
	 */
	public List<UUserRole> haveRolelist(String user_id){
		List<UUserRole> list = new ArrayList<UUserRole>();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select distinct b.role_id userId,concat(b.role_id,b.role_name)roleId from u_user_role a,u_role b where a.user_id=? and a.role_id=b.role_id  and b.state=1 ";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UUserRole role = new UUserRole();
				role.setUserId(rs.getString("userId"));
				role.setRoleId(rs.getString("roleId"));
				list.add(role);
			}
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @Method: lackRolelist    
	 * @Description: 获取用户缺少的角色
	 * @author: marx.liao
	 * @date 2016-4-29 下午3:27:52
	 * @param user_id
	 * @return       
	 * @throws
	 */
	public List<UUserRole> lackRolelist(String user_id){
		List<UUserRole> list = new ArrayList<UUserRole>();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select distinct c.role_id userId,concat(c.role_id,'|',c.role_name)roleId from u_role c where c.state=1 and c.role_id not in(select distinct b.role_id from u_user_role a,u_role b where a.user_id=? and a.role_id=b.role_id  and b.state=1 ) ";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UUserRole role = new UUserRole();
				role.setUserId(rs.getString("userId"));
				role.setRoleId(rs.getString("roleId"));
				list.add(role);
			}
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @Method: insertUserRole    
	 * @Description: 添加用户角色
	 * @author: marx.liao
	 * @date 2016-4-29 下午3:33:06
	 * @param bean       
	 * @throws
	 */
	public void insertUserRole(UUserRole bean){
		Connection con = ConnectionUtil.getDBConnection();
		String sql = " INSERT INTO u_user_role (user_id,role_id, state,mod_user,mod_time) VALUES (?,?,?,?,NOW())";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUserId());
			pstmt.setString(2, bean.getRoleId());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getModUser());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Method: deleteUserRole    
	 * @Description: 删除用户角色
	 * @author: marx.liao
	 * @date 2016-4-29 下午3:33:34
	 * @param bean       
	 * @throws
	 */
	public void deleteUserRole(UUserRole bean){
		Connection con = ConnectionUtil.getDBConnection();
		StringBuffer sql = new StringBuffer(" delete from u_user_role where 1=1");
		PreparedStatement pstmt;
		try {
			if(bean.getUserId() !=null && !bean.getUserId().equals("")){
				sql.append(" and user_id= '"+bean.getUserId()+"'");
			}
			if(bean.getRoleId() !=null && !bean.getRoleId().equals("")){
				sql.append(" and role_id= '"+bean.getRoleId()+"'");
			}
			pstmt = con.prepareStatement(sql.toString());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
