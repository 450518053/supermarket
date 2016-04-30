package com.pojo;

import java.util.List;

/**                    
 * @Filename Commodity.java
 *
 * @Description 货品
 *
 * @author tcc 2016年4月29日
 *
 * @email 450518053@qq.com
 *
 */
public class Commodity {
	
	private String			commoNo;
	
	private String			commoName;
	
	private String			type;		//货品类别
										
	private String			unit;		//单位
										
	private List<Product>	prods;		//商品
										
	public String getCommoNo() {
		return commoNo;
	}
	
	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}
	
	public String getCommoName() {
		return commoName;
	}
	
	public void setCommoName(String commoName) {
		this.commoName = commoName;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public List<Product> getProds() {
		return prods;
	}
	
	public void setProds(List<Product> prods) {
		this.prods = prods;
	}
	
	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Commodity [commoNo=" + commoNo + ", commoName=" + commoName + ", type=" + type
				+ ", unit=" + unit + ", prods=" + prods + "]";
	}
	
}
