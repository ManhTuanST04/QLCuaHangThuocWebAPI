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

import com.api.DAO.RoleDAO;
import com.api.model.*;

@RestController
public class RoleController {
	@Autowired
	private RoleDAO roleDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@GetMapping(path = "/api/role/getallrole")
	public List<RoleModel> GetAllRole() {
		try {
			List<RoleModel> lst = roleDAO.GetAllRole();
			logger.info("Lấy danh sách Role");
			return lst;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}
	
	@GetMapping(path = "/api/role/getroleuser")
	public List<RoleModel> GetRoleUser(int userId) {
		try {
			List<RoleModel> lst = roleDAO.GetRoleUser(userId);
			logger.info("Lấy danh sách Role của User"+ userId);
			return lst;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}
	
	@PostMapping(path = "/api/role/assignroleuser")
	public int AssignRoleForUser(@RequestBody UserRoleModel userRoleModel) {
		try {
			roleDAO.AssignRoleForUser(userRoleModel);
			return 1;
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			return 0;
		}
		
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
