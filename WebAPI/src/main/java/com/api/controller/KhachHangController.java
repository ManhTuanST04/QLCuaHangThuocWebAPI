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
public class KhachHangController {
	@Autowired
	private KhachHangDAO khDAO;
	
	@GetMapping(path = "/api/account/khachhanglogin")
	public KhachHangModel KhachHangLogin(String dienthoai, String matkhau) {
		KhachHangModel model = khDAO.KhachHangLogin(dienthoai, matkhau);
		return model;
	}
}
