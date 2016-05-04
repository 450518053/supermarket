package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Product;
import com.util.ConnectionUtil;

/**   
 * @ClassName: SealManagerDao  
 * @Description:   销售DAO
 *     
 */
public class SealManagerDao {
	
	/**
	 * 
	 * @Method: getProduct    
	 * @Description: 获得产品详细信息
	 */
	public List<Product> getProduct(){
		List<Product> list = new ArrayList<Product>();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select * from product";
		PreparedStatement pstmt;
		try{
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setCommoNo(rs.getString("commo_no"));
				product.setProdNo(rs.getString("prod_no"));
				product.setMarketPrice(rs.getDouble("market_price"));
				product.setSellPrice(rs.getDouble("sell_price"));
				product.setProp(rs.getString("prop"));
				product.setProdName(rs.getString("prod_name"));
				product.setSalesVolume(rs.getInt("sales_volume"));
				list.add(product);
			}
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
