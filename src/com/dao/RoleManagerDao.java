package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pojo.URole;
import com.pojo.UUserLogin;
import com.util.ConnectionUtil;

/**   
 * @ClassName: RoleManagerDao  
 * @Description:  角色管理类 
 * @author: marx.liao
 * @date 2016-4-29 下午3:52:59  
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
			sql.append(" and user_id like '%"+role_id+"%'" );
		}
		if(role_name != null && !role_name.equals("")){
			sql.append(" and user_name like '%"+role_name+"%'" );
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
			sql.append(" and user_id like '%"+role_id+"%'");
		}
		if(role_name != null && !role_name.equals("")){
			sql.append(" and user_name like '%"+role_name+"%'");
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

}
