package com.pojo;

public class OrderItem {

	private int itemNo;

	private int orderNo;

	private String commo_name;// 货品名

	private String prod_name;// 商品名

	private String type;// 商品类型

	private String prop;// 商品规格

	private int quantity;// 数量

	private double price;// 总价

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getCommo_name() {
		return commo_name;
	}

	public void setCommo_name(String commo_name) {
		this.commo_name = commo_name;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderItem [itemNo=" + itemNo + ", orderNo=" + orderNo + ", commo_name=" + commo_name + ", prod_name="
				+ prod_name + ", type=" + type + ", prop=" + prop + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
