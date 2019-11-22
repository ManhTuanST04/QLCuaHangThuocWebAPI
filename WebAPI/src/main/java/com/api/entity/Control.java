package com.api.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "control")
public class Control {
	public Control() {
		
	}
	
	public Control(Integer id, String name, String code, Permission per, Collection<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.permission = per;
		this.roles = roles;
	}
	
	public Control(Integer id, String name, String code, Permission per) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.permission = per;
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "permissionid", nullable = false)
	private Permission permission;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	//Bảng role control
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "role_control", 
	    joinColumns = { @JoinColumn(name = "controlid") }, 
	    inverseJoinColumns = {@JoinColumn(name = "roleid") })
	private Collection<Role> roles;

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	
}
