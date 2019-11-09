package com.api.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "role_permission", 
	    joinColumns = { @JoinColumn(name = "roleid") }, 
	    inverseJoinColumns = {@JoinColumn(name = "permissionid") })
	private Collection<Permission> permissions;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", 
	    joinColumns = { @JoinColumn(name = "roleid") }, 
	    inverseJoinColumns = {@JoinColumn(name = "userid") })
	private Collection<User> users;


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


	public Collection<Permission> getPermissions() {
		return permissions;
	}


	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}


	public Collection<User> getUsers() {
		return users;
	}


	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Integer id, String name, String code , Collection<Permission> permissions, Collection<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.permissions = permissions;
		this.users = users;
		this.code = code;
	}
	
	public Role(Integer id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
}








