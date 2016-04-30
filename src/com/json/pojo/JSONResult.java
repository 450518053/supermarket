package com.json.pojo;

import java.util.List;

/**                    
 * @Filename JSONResult.java
 *
 * @Description 
 *
 *
 */
public class JSONResult {
	
	private boolean	success;
	
	private String	message;
	
	private List<?>	list;
	
	/**
	 * 构建一个<code>JSONResult.java</code>
	 */
	public JSONResult() {
		super();
		this.success = false;
	}
	
	/**
	 * 构建一个<code>JSONResult.java</code>
	 * @param success
	 * @param message
	 */
	public JSONResult(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<?> getList() {
		return list;
	}
	
	public void setList(List<?> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "JSONResult [success=" + success + ", message=" + message + ", list=" + list + "]";
	}
	
}
