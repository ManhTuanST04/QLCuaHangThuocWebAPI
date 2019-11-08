package com.api.model;

public class UserRoleModel {
	private int userId;
	private String sRole;
	
	public UserRoleModel(int userId, String sRole) {
		super();
		this.userId = userId;
		this.sRole = sRole;
	}
	
	public UserRoleModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getsRole() {
		return sRole;
	}
	
	public void setsRole(String sRole) {
		this.sRole = sRole;
	};
	
	
}
