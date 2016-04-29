package com.pojo;


public class UUserLogin {
	
    private String userId;			//用户ID

    private String userName;		//用户名

    private String userPwd;			//密码

    private String userType;		//用户类型

    private String isLogin;			//是否登录

    private String state;			//有效状态

    private String lastLoginTime;	//最后登录时间

    private String lastLoginIp;		//最后登录IP

    private String dept;			//部门

    private String unregistTime;	//注销时间

    private String unregister;		//注销人
    
	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getUnregistTime() {
        return unregistTime;
    }

    public void setUnregistTime(String unregistTime) {
        this.unregistTime = unregistTime;
    }

    public String getUnregister() {
        return unregister;
    }

    public void setUnregister(String unregister) {
        this.unregister = unregister;
    }
    
}