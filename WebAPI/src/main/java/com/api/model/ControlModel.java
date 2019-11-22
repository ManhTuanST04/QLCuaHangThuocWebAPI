package com.api.model;

public class ControlModel {
	public ControlModel() {
	}

	public ControlModel(Integer id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
	public ControlModel(Integer id, String name, String code,int permissionId) {
		this.id = id;
		this.name = name;
		this.permissionId = permissionId;
		this.code = code;
	}
	
	public ControlModel(Integer id, String name, String code,int permissionId, int roleId) {
		this.id = id;
		this.name = name;
		this.permissionId = permissionId;
		this.code = code;
		this.roleId = roleId;
	}
	
	private Integer id;
	
	private String name;
	
	private Integer permissionId;

	private String code;
	
	private Integer roleId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
//
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}
