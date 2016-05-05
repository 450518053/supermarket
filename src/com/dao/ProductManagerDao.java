package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public boolean update(Product prodParam) {
		boolean isInsert = true;
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select * from product where prod_no = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prodParam.getProdNo());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("prod_no") == null || rs.getString("prod_no").equals("")) {
					isInsert = false;
					break;
				}
			}
			pstmt.close();
			
			if (isInsert) {
				//新增
				sql = " INSERT INTO product (prod_no,commo_no,prod_name,prop,market_price,sell_price,sales_volume) VALUES (?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.valueOf(prodParam.getProdNo()));
				pstmt.setInt(2, Integer.valueOf(prodParam.getCommoNo()));
				pstmt.setString(3, prodParam.getProdName());
				pstmt.setString(4, prodParam.getProp());
				pstmt.setDouble(5, prodParam.getMarketPrice());
				pstmt.setDouble(6, prodParam.getSellPrice());
				pstmt.setInt(7, prodParam.getSalesVolume());
				pstmt.executeUpdate();
				pstmt.close();
			} else {
				//修改
				sql = " update  product set commo_no = ?,prod_name = ?,prop = ?,market_price = ?,sell_price = ?,sales_volume = ? where prod_no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.setInt(1, Integer.valueOf(prodParam.getCommoNo()));
				pstmt.setString(2, prodParam.getProdName());
				pstmt.setString(3, prodParam.getProp());
				pstmt.setDouble(4, prodParam.getMarketPrice());
				pstmt.setDouble(5, prodParam.getSellPrice());
				pstmt.setInt(6, prodParam.getSalesVolume());
				pstmt.setInt(7, Integer.valueOf(prodParam.getProdNo()));
				pstmt.executeUpdate();
				pstmt.close();
			}
			
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Product getById(String prodNo) {
		String sql = "select * from product where prod_no = ?";
		Product prod = null;
		PreparedStatement pstmt = null;
		Connection con = ConnectionUtil.getDBConnection();
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, Integer.valueOf(prodNo));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				prod = new Product();
				prod.setCommoNo(rs.getString("commo_no"));
				prod.setMarketPrice(rs.getDouble("market_price"));
				prod.setProdName(rs.getString("prod_name"));
				prod.setProdNo(rs.getString("prod_no"));
				prod.setProp(rs.getString("prop"));
				prod.setSalesVolume(rs.getInt("sales_volume"));
				prod.setSellPrice(rs.getDouble("sell_price"));
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prod;
	}
	
	public boolean deleteById(String prodNo) {
		String sql = "delete from product where prod_no = ?";
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ConnectionUtil.getDBConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, Integer.valueOf(prodNo));
			return pstmt.executeUpdate() != 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return false;
	}
	
}
