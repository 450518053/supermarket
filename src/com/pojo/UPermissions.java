package com.pojo;

public class UPermissions {
	
	private String	permissionsId; 		//权限ID
	
	private String	permissionsName;    //权限名称
	
	private String	url;				//权限匹配的url
	
	private String	state;				//有效状态
	
	public String getPermissionsId() {
		return permissionsId;
	}
	
	public void setPermissionsId(String permissionsId) {
		this.permissionsId = permissionsId;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPermissionsName() {
		return permissionsName;
	}
	
	public void setPermissionsName(String permissionsName) {
		this.permissionsName = permissionsName;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
}