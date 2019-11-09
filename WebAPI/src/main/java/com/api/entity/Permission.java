package com.api.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission {
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
    joinColumns = { @JoinColumn(name = "permissionid") }, 
    inverseJoinColumns = {@JoinColumn(name = "roleid") })
	private Collection<Role> roles;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "permission")
	private Collection<Control> lstControl;
	
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
	
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Permission(Integer id, String name, Collection<Role> roles,  Collection<Control> lstControl) {
		super();
		this.id = id;
		this.name = name;
		this.roles = roles;
		this.lstControl = lstControl;
	}

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
