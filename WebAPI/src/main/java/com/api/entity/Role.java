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
	
	//Bảng role control
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "role_control", 
	    joinColumns = { @JoinColumn(name = "roleid") }, 
	    inverseJoinColumns = {@JoinColumn(name = "controlid") })
	private Collection<Control> controls;

	public void RemoveAllUser() {
		users.clear();
	}
	
	public void RemoveAllPermission() {
		permissions.clear();
	}
	

	public void RemovePerForRole(Permission per) {
		permissions.remove(per);
		per.getRoles().remove(this);
	}
	
	public void AddPerForRole(Permission per) {
		permissions.add(per);
		//per.getRoles().add(this);
	}
	
	public void AddControlForRole(Control control) {
		controls.add(control);
		//per.getRoles().add(this);
	}
	
	public void RemoveControlForRole(Control con) {
		controls.remove(con);
		con.getRoles().remove(this);
	}
	
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

	//role control
	public Collection<Control> getControls() {
		return controls;
	}

	public void setControls(Collection<Control> controls) {
		this.controls = controls;
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

	public Role(Integer id, String name, String code , Collection<Permission> permissions, Collection<User> users, Collection<Control> controls) {
		super();
		this.id = id;
		this.name = name;
		this.permissions = permissions;
		this.users = users;
		this.code = code;
		this.controls = controls;
	}
	
	public Role(Integer id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
}








