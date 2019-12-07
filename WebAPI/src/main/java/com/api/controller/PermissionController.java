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
	
	@GetMapping(path = "/api/permission/assignperforrole")
	public int AssignPerForRole(int roleId, int perId) {
		try {
			int res = 0;
			res = perDAO.AssignPermissionForRole(roleId, perId);
			return res;
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	@GetMapping(path = "/api/permission/removeperforrole")
	public int RemovePerForRole(int roleId, int perId) {
		try {
			int res = 0;
			res = perDAO.RemovePerForRole(roleId, perId);
			return res;
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	@PostMapping(path = "/api/permission/addnewper")
	public int ThemMoiQuyen(@RequestBody PermissionModel model) {
		try {
			int res = perDAO.AddNewPer(model);
			return res;
		}
		catch(Exception ex) {
			return 0;
		}
	}
}
