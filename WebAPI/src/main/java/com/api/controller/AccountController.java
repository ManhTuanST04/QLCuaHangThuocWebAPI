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

import com.api.DAO.*;
import com.api.entity.*;
import com.api.model.*;
import com.google.gson.Gson;

@RestController
public class AccountController {
	@Autowired
	private UserDAO userDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@PostMapping(path = "/api/account/login")
	public UserModel Login(@RequestBody UserModel model) {
		try {
			UserModel um = userDAO.Login(model.getUserName(), model.getPassWord());	
			logger.info("Đăng nhập");
			return um;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}
	
	@GetMapping(path = "/api/account/lstuser")
	public List<UserModel> GetListUser() {
		try {
			List<UserModel> lst = userDAO.GetListUser();
			logger.info("Lấy danh sách User");
			return lst;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}
	
	@GetMapping(path = "/api/account/checkduplicateuser")
	public int CheckDuplicateUser(String userName) {
		try {
			int res = userDAO.CheckDuplicateUser(userName);
			logger.info("Kiểm tra trùng User khi thêm mới");
			return res;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			return -1;
		}
	}
	
	@PostMapping(path = "/api/account/addorupdateuser")
	public int AddOrInsertUser(@RequestBody UserModel model) {
		try {
			int res = userDAO.InsertOrUpdateUser(model);	
			logger.info("Đăng nhập");
			return res;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			return 0;
		}
	}
	
	@GetMapping(path = "/api/account/getuserbyid")
	public UserModel GetUserById(int id) {
		try {
			UserModel user = userDAO.GetUserById(id);	
			logger.info("Lấy User từ Id");
			return user;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}
	
	@GetMapping(path = "/api/account/deleteuser")
	public int DeleteUser(int id) {
		try {
			int res = userDAO.DeleteUser(id);	
			logger.info("Xóa User!");
			return res;
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			return 0;
		}
	}
	
	@GetMapping(path = "/api/account/getperuser")
	public List<PermissionModel> GetListPerUser(int userId){
		try {
			List<PermissionModel> lst = userDAO.GetPerUser(userId);
			return lst;
		}
		catch(Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}
	
	@GetMapping(path = "/api/account/getcontroluser")
	public List<ControlModel> GetListControlUser(int userId){
		try {
			List<ControlModel> lst = userDAO.GetListControlUser(userId);
			return lst;
		}
		catch(Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}
	
}
