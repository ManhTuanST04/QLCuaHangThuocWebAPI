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
	
	@GetMapping(path = "/api/khachhang/khachhanglogin")
	public KhachHangModel KhachHangLogin(String dienthoai, String matkhau) {
		KhachHangModel model = khDAO.KhachHangLogin(dienthoai, matkhau);
		return model;
	}
	
	@GetMapping(path = "/api/khachhang/getallKh")
	public List<KhachHangModel> DanhSachKhachHang() {
		List<KhachHangModel> lst = khDAO.DanhSachKhachHang();
		return lst;
	}
	
	@GetMapping(path = "/api/khachhang/getKhById")
	public KhachHangModel GetKhachHangById(int idKH) {
		KhachHangModel model = khDAO.GetKhachHangById(idKH);
		return model;
	}
	
	@PostMapping(path = "/api/khachhang/addkhachhang")
	public int ThemKhachHang(@RequestBody KhachHangModel model) {
		try {
			int res = khDAO.AddKhachHang(model);
			return res;
		}
		catch(Exception ex) {
			return 0;
		}
	}
	
	@PostMapping(path = "/api/khachhang/suakhachhang")
	public int SuaKhachHang(@RequestBody KhachHangModel model) {
		try {
			int res = khDAO.EditKhachHang(model);
			return res;
		}
		catch(Exception ex) {
			return 0;
		}
	}
	
	@GetMapping(path = "/api/khachhang/xoakhachhang")
	public int XoaKhachHang(int idKH) {
		try {
			int res = khDAO.DeleteKhachHang(idKH);
			return res;
		}
		catch(Exception ex) {
			return 0;
		}
	}
}
