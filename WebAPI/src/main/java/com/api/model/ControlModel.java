package com.api.model;

public class ControlModel {
	public ControlModel() {
	}

	public ControlModel(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ControlModel(Integer id, String name, int permissionId) {
		this.id = id;
		this.name = name;
		this.permissionId = permissionId;
	}
	
	private Integer id;
	
	private String name;
	
	private Integer permissionId;

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
	
	
}
