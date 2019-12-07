package com.api.controller;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.api.entity.Role;
import com.api.model.ChiTietDonHangModel;
import com.api.model.ChiTietDonHangModel2;
import com.api.model.DonDatHangModel;
import com.api.model.KhachHangModel;
import com.api.model.UserModel;

@RestController
public class DonHangController {
	@Autowired
	private DonHangDAO dao;
	
	@PostMapping(path = "/api/donhang/themdonhang")
	public int ThemDonHang(@RequestBody DonDatHangModel abc) {
		int res = dao.ThemDonHang(abc);
		return res;
	}
	
	@PostMapping(path = "/api/donhang/themchitietdonhang")
	public int ThemChiTietDonHang(@RequestBody ChiTietDonHangModel ctdh) {
		dao.ThemChiTietDonHang(ctdh);

		return 1;
	}
	
	@GetMapping(path = "/api/donhang/dsdhuser")
	public List<DonDatHangModel> DanhSachDonHangNguoiDung(int idUser) {
		try {
			List<DonDatHangModel> lst = dao.DonHangCuaNguoiDung(idUser);
			return lst;
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	@GetMapping(path = "/api/donhang/ctdh2")
	public List<ChiTietDonHangModel2> ChiTietDonHangTheoDon(int idDH) {
		try {
			List<ChiTietDonHangModel2> lst = dao.ChiTietDonHangTheoDon(idDH);
			return lst;
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	@GetMapping(path = "/api/donhang/getalldonhang")
	public List<DonDatHangModel> DanhSachDonHang() {
		try {
			List<DonDatHangModel> lst = dao.LayTatCaDonHang();
			return lst;
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	@GetMapping(path = "/api/donhang/xacnhandonhang")
	public int XacNhanDon(int idDH) {
		try {
			int res = dao.XacNhanDonHang(idDH);
			return res;
		}
		catch (Exception ex) {
			return 0;
		}
	}
	
	@GetMapping(path = "/api/donhang/huydon")
	public int HuyDon(int idDH) {
		try {
			int res = dao.HuyDonHang(idDH);
			return res;
		}
		catch (Exception ex) {
			return 0;
		}
	}
}
