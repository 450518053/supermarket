package com.pojo;


public class URolePe {

	private String	roleId;  		//角色id
	
	private String	permissionsId;	//权限id
	
	private String	state;			//有效状态
	
	private String	modUser;		//修改人员
	
	private String	modTime;		//修改时间

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(String permissionsId) {
		this.permissionsId = permissionsId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getModUser() {
		return modUser;
	}

	public void setModUser(String modUser) {
		this.modUser = modUser;
	}

	public String getModTime() {
		return modTime;
	}

	public void setModTime(String modTime) {
		this.modTime = modTime;
	}
}