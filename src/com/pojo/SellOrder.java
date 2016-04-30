package com.pojo;

import java.util.Date;

/**
 * 销售订单
 * @author Administrator
 *
 */
public class SellOrder {

	private int orderNo;

	private int quantity;//购买商品的数量

	private double totalPrice;//总价

	private Date createTime;

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SellOrder [orderNo=" + orderNo + ", quantity=" + quantity + ", totalPrice=" + totalPrice
				+ ", createTime=" + createTime + "]";
	}

	
	
}
