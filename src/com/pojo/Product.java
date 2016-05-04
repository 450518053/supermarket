package com.pojo;


/**                    
 * @Filename Product.java
 *
 * @Description 商品
 *
 * @author tcc 2016年4月29日
 *
 * @email 450518053@qq.com
 *
 */
public class Product {
	
	private String	commoNo;
	
	private String	prodNo;
	
	private String	prodName;
	
	private String	prop;			//规格
									
	private double	marketPrice;	//市场价/进价
									
	private double	sellPrice;		//卖价
									
	private int		salesVolume;	//销量
									
	public String getCommoNo() {
		return commoNo;
	}
	
	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}
	
	public String getProdNo() {
		return prodNo;
	}
	
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	
	public String getProdName() {
		return prodName;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public String getProp() {
		return prop;
	}
	
	public void setProp(String prop) {
		this.prop = prop;
	}
	
	public double getMarketPrice() {
		return marketPrice;
	}
	
	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	public double getSellPrice() {
		return sellPrice;
	}
	
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public int getSalesVolume() {
		return salesVolume;
	}
	
	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [commoNo=" + commoNo + ", prodNo=" + prodNo + ", prodName=" + prodName
				+ ", prop=" + prop + ", marketPrice=" + marketPrice + ", sellPrice=" + sellPrice
				+ ", salesVolume=" + salesVolume + "]";
	}
	
}
