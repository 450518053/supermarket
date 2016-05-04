package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Commodity;
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
	
	/**
	 * 
	 * @Method: insertSellOrder    
	 * @Description: 添加销售清单
	 * @param totalNum
	 * @param totalPrice
	 * @return       
	 * @throws
	 */
	public int insertSellOrder(int totalNum,double totalPrice){
		int i = 0;
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "insert into sell_order(quantity,total_price,create_time) values(?,?,NOW());";
		PreparedStatement pstmt;
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, totalNum);
			pstmt.setDouble(2, totalPrice);
			pstmt.executeUpdate();
			pstmt.close();
			//con.close();
			
			pstmt = con.prepareStatement("select max(order_no)  from sell_order");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				i = rs.getInt("max(order_no)");
			}
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return i;
	}
	
	public Product selectProductById(int prod_no){
		Product pro = new Product();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select * from product where prod_no = ?";
		PreparedStatement pstmt;
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prod_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pro.setCommoNo(rs.getString("commo_no"));
				pro.setProdNo(rs.getString("prod_no"));
				pro.setMarketPrice(rs.getDouble("market_price"));
				pro.setSellPrice(rs.getDouble("sell_price"));
				pro.setProp(rs.getString("prop"));
				pro.setProdName(rs.getString("prod_name"));
				pro.setSalesVolume(rs.getInt("sales_volume"));
			}
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pro;
	}
	
	public Commodity selectCommodityById(int commo_no){
		Commodity com = new Commodity();
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "select * from commodity where commo_no = ?";
		PreparedStatement pstmt;
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commo_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				com.setCommoNo(rs.getString("commo_no"));
				com.setCommoName(rs.getString("commo_name"));
				com.setType(rs.getString("type"));
				com.setUnit(rs.getString("unit"));
			}
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return com;
	}
	
	public void insertOrderItem(Product pro,Commodity com,int num,double price,int order_no){
		Connection con = ConnectionUtil.getDBConnection();
		String sql = "insert into order_item(order_no,commo_name,prod_name,type,prop,quantity,price) values(?,?,?,?,?,?,?);";
		PreparedStatement pstmt;
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, order_no);
			pstmt.setString(2, com.getCommoName());
			pstmt.setString(3, pro.getProdName());
			pstmt.setString(4, com.getType());
			pstmt.setString(5, pro.getProp());
			pstmt.setInt(6, num);
			pstmt.setDouble(7, price);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
