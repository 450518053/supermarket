package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Product;
import com.util.ConnectionUtil;

/**                    
 * @Filename ProductManagerDao.java
 *
 * @Description 
 *
 * @author tcc 2016年5月3日
 *
 * @email 450518053@qq.com
 *
 */
public class ProductManagerDao {
	
	public int getCount(Product prodParam) {
		int count = 0;
		Connection con = ConnectionUtil.getDBConnection();
		StringBuffer sql = new StringBuffer("select count(1) as allcount from product where 1=1");
		if (prodParam.getCommoNo() != null && !prodParam.getCommoNo().equals("")) {
			sql.append(" and commo_no = " + prodParam.getCommoNo() + "");
		}
		if (prodParam.getProdNo() != null && !prodParam.getProdNo().equals("")) {
			sql.append(" and prod_no = " + prodParam.getProdNo() + "");
		}
		if (prodParam.getProdName() != null && !prodParam.getProdName().equals("")) {
			sql.append(" and prod_no like '%" + prodParam.getProdName() + "%'");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Product> getProds(Product prodParam, int rows, int page) {
		List<Product> list = new ArrayList<Product>();
		Connection con = ConnectionUtil.getDBConnection();
		StringBuffer sql = new StringBuffer("select * from product where 1=1");
		int startRow = (page - 1) * rows;
		if (prodParam.getCommoNo() != null && !prodParam.getCommoNo().equals("")) {
			sql.append(" and commo_no = " + prodParam.getCommoNo() + "");
		}
		if (prodParam.getProdNo() != null && !prodParam.getProdNo().equals("")) {
			sql.append(" and prod_no = " + prodParam.getProdNo() + "");
		}
		if (prodParam.getProdName() != null && !prodParam.getProdName().equals("")) {
			sql.append(" and prod_name like '%" + prodParam.getProdName() + "%'");
		}
		sql.append(" limit ?,?");
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, rows);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Product prod = new Product();
				prod.setCommoNo(rs.getString("commo_no"));
				prod.setMarketPrice(rs.getDouble("market_price"));
				prod.setProdName(rs.getString("prod_name"));
				prod.setProdNo(rs.getString("prod_no"));
				prod.setProp(rs.getString("prop"));
				prod.setSalesVolume(rs.getInt("sales_volume"));
				prod.setSellPrice(rs.getDouble("sell_price"));
				list.add(prod);
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
