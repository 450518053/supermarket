package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.ConnectionUtil;

/**
 * 数据分析Dao
 *
 */
public class DataManagerDao {
	
	public  List<List<String>> getDataSet(String start_time,String end_time){
		List<List<String>> lists = new ArrayList<List<String>>();
		List<String> list = new ArrayList<String>();
		int temp = 0;
		Connection con = ConnectionUtil.getDBConnection();
		StringBuffer sql = new StringBuffer("select a.commo_name,b.order_no from sell_order b,order_item a where a.order_no = b.order_no and b.create_time BETWEEN ? and ?;");
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, start_time);
			pstmt.setString(2, end_time);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
//				for(int i = 0;i<rs.getInt("quantity");i++){
//					list.add(rs.getString("commo_name"));
//				}
				if(temp == rs.getInt("order_no")){
					list.add(rs.getString("commo_name"));
				}else{
					temp = rs.getInt("order_no");
					if(list.isEmpty()){
						list.add(rs.getString("commo_name"));
					}else{
						lists.add(list);
						list.clear();
						list.add(rs.getString("commo_name"));
					}
					
				}
			}
			lists.add(list); //添加最后一次
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}

}
