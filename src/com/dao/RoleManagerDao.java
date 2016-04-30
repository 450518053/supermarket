package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pojo.UPermissions;
import com.pojo.URole;
import com.pojo.URolePe;
import com.pojo.UUserLogin;
import com.pojo.UUserRole;
import com.util.CommonUtil;
import com.util.ConnectionUtil;

/**   
 * @ClassName: RoleManagerDao  
 * @Description:  角色管理类 
 *     
 */
public class RoleManagerDao {
	
	/**
	 * 
	 * @Method: getRoleCount    
	 * @Description: 获取角色总数
	 * @author: marx.liao
	 * @date 2016-4-29 下午4:41:28
	 * @param role_id
	 * @param role_name
	 * @return       
	 * @throws
	 */
	public int getRoleCount(String role_id,String role_name){
		int count = 0;
		
		Connection con = ConnectionUtil.getDBConnection();
		StringBuffer sql = new StringBuffer("select count(1) as allcount from u_role where 1=1");
		if(role_id != null && !role_id.equals("")){
			sql.append(" and role_id like '%"+role_id+"%'" );
		}
		if(role_name != null && !role_name.equals("")){
			sql.append(" and role_name like '%"+role_name+"%'" );
		}
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
	 * @Method: getRoles    
	 * @Description: 获取角色信息
	 * @author: marx.liao
	 * @date 2016-4-29 下午4:41:39
	 * @param role_id
	 * @param role_name
	 * @param rows
	 * @param page
	 * @return       
	 * @throws
	 */
	public List<URole> getRoles(String role_id,String role_name,int rows,int page){
		List<URole> list = new ArrayList<URole>();
		Connection con = ConnectionUtil.getDBConnection();
		StringBuffer sql = new StringBuffer("select * from u_role where 1=1");
		int startRow = (page - 1) * rows;
		if(role_id != null && !role_id.equals("")){
			sql.append(" and role_id like '%"+role_id+"%'");
		}
		if(role_name != null && !role_name.equals("")){
			sql.append(" and role_name like '%"+role_name+"%'");
		}
		sql.append(" limit ?,?");
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, rows);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				URole role = new URole();
				role.setRoleId(rs.getString("role_id"));
				role.setRoleName(rs.getString("role_name"));
				role.setState(rs.getString("state"));
				role.setModUser(rs.getString("mod_user"));
				role.setModTime(rs.getString("mod_time"));
				list.add(role);
			}
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据id查询角色
	 * @param role_id
	 * @return
	 */
	public URole queryRoleById(String role_id){
		URole role = new URole();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select * from u_role where role_id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, role_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				role.setRoleId(rs.getString("role_id"));
				role.setRoleName(rs.getString("role_name"));
				role.setState(rs.getString("state"));
				role.setModUser(rs.getString("mod_user"));
				role.setModTime(rs.getString("mod_time"));
			}
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
	
	/**
	 * 添加角色
	 * @param role_id
	 * @param role_name
	 * @return
	 */
	public boolean addRole(String role_id,String role_name){
		boolean flag = false;
		Connection con = ConnectionUtil.getDBConnection();
		String sql = " INSERT INTO u_role (role_id,role_name,state) VALUES (?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, role_id);
			pstmt.setString(2, role_name);
			pstmt.setString(3, "1");
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
	 * 修改角色
	 * @param role_id
	 * @param role_name
	 * @return
	 */
	public boolean updateRole(String role_id,String role_name){
		boolean flag = false;
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "update  u_role set role_name = ? where role_id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, role_name);
			pstmt.setString(2, role_id);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean logOutRole(String role_id,String mod_user){
		boolean flag = false;
		Connection con = ConnectionUtil.getDBConnection();
		StringBuffer sql = new StringBuffer("update u_role set state='0',mod_user=?,mod_time=now() where 1=1"); 
		if(role_id !=null && !role_id.equals("")){
			sql.append(" and role_id = '"+role_id+"' ");
		}
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mod_user);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		flag = true;
		return flag;
	}
	
	/**
	 * 激活角色
	 * @param role_id
	 * @return
	 */
	public boolean ActivateRole(String role_id){
		boolean flag = false;
		Connection con = ConnectionUtil.getDBConnection();
		StringBuffer sql = new StringBuffer("update u_role set state='1',mod_user='',mod_time=null where 1=1"); 
		if(role_id !=null && !role_id.equals("")){
			sql.append(" and role_id = '"+role_id+"' ");
		}
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		flag = true;
		return flag;
	}
	
	/**
	 * 
	 * @Method: haveRolelist    
	 * @Description: 获取角色拥有的权限
	 * @author: marx.liao
	 * @date 2016-4-29 下午3:27:39
	 * @param user_id
	 * @return       
	 * @throws
	 */
	public List<URolePe> havePermissionlist(String role_id){
		List<URolePe> list = new ArrayList<URolePe>();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select distinct b.permissions_id roleId,b.permissions_name permissionsId from u_role_pe a,u_permissions b where  b.state='1' and a.permissions_id=b.permissions_id and a.role_id=? order by b.sort; ";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, role_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				URolePe permission = new URolePe();
				permission.setPermissionsId(rs.getString("permissionsId"));
				permission.setRoleId(rs.getString("roleId"));
				list.add(permission);
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
	 * @Description: 获取角色缺少的权限
	 * @author: marx.liao
	 * @date 2016-4-29 下午3:27:52
	 * @param user_id
	 * @return       
	 * @throws
	 */
	public List<URolePe> lackPermissionlist(String role_id){
		List<URolePe> list = new ArrayList<URolePe>();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select distinct a.permissions_id roleId,a.permissions_name permissionsId from u_permissions a where  state = 1 and a.permissions_id not in (select b.permissions_id from u_role_pe b where b.role_id=?) order by a.sort; ";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, role_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				URolePe permission = new URolePe();
				permission.setPermissionsId(rs.getString("permissionsId"));
				permission.setRoleId(rs.getString("roleId"));
				list.add(permission);
			}
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 添加权限
	 * @param bean
	 */
	public void insertRoleAuto(URolePe bean){
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "INSERT INTO u_role_pe (permissions_id,role_id, state,mod_user,mod_time) VALUES(?,?,?,?,NOW())";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getPermissionsId());
			pstmt.setString(2, bean.getRoleId());
			pstmt.setString(3, "1");
			pstmt.setString(4, bean.getModUser());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除权限
	 * @param bean
	 */
	public void deleteRoleAuto(URolePe bean){
		Connection con = ConnectionUtil.getDBConnection();
		StringBuffer sql = new StringBuffer("delete from u_role_pe where 1=1 ");
		if(bean.getPermissionsId() !=null && !bean.getPermissionsId().equals("")){
			sql.append("and permissions_id='"+bean.getPermissionsId()+"' ");
		}
		if(bean.getRoleId() !=null && !bean.getRoleId().equals("")){
			sql.append("and role_id='"+bean.getRoleId()+"' ");
		}
		PreparedStatement pstmt;
		try {
		pstmt = con.prepareStatement(sql.toString());
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取角色拥有的用户
	 * @param role_id
	 * @return
	 */
	public List<UUserRole> haveRole(String role_id){
		List<UUserRole> list = new ArrayList<UUserRole>();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select distinct b.user_id,concat(b.user_id,'|',b.user_name)role_id from u_user_role a,u_user_login b where  b.state='1' and a.user_id=b.user_id and a.role_id=? order by b.user_id ";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, role_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UUserRole bean = new UUserRole();
				bean.setUserId(rs.getString("user_id"));
				bean.setRoleId(rs.getString("role_id"));
				list.add(bean);
			}
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 获取角色缺少的用户
	 * @param role_id
	 * @return
	 */
	public List<UUserRole> lackRole(String role_id){
		List<UUserRole> list = new ArrayList<UUserRole>();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select distinct a.user_id,concat(a.user_id,'|',a.user_name)role_id from u_user_login a where  state = 1 and a.user_id not in (select b.user_id from u_user_role b where b.role_id=?) order by a.user_id";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, role_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UUserRole bean = new UUserRole();
				bean.setUserId(rs.getString("user_id"));
				bean.setRoleId(rs.getString("role_id"));
				list.add(bean);
			}
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
