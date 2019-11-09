package com.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.DAO.PermissionDAO;
import com.api.DAO.RoleDAO;
import com.api.model.*;

@RestController
public class PermissionController {
	@Autowired
	private PermissionDAO perDAO;
	
	@GetMapping(path = "/api/permission/getallper")
	public List<PermissionModel> GetAllPermission(){
		List<PermissionModel> lst = perDAO.GetAllPermission();
		return lst;
	}
	
	@GetMapping(path = "/api/permission/getperrole")
	public List<PermissionModel> GetPermissionRole(int roleId){
		List<PermissionModel> lst = perDAO.GetPermissionRole(roleId);
		return lst;
	}
	
}
