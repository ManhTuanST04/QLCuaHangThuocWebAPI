package com.api.model;

import javax.persistence.Column;

public class KhachHangModel {
	private int id;

	private String dienThoai;
	
	private String matKhau;

	private String ten;

	private String email;
	
	private String diaChi;
	
	public KhachHangModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhachHangModel(int id, String dienThoai, String matKhau, String ten, String email, String diaChi) {
		super();
		this.id = id;
		this.dienThoai = dienThoai;
		this.matKhau = matKhau;
		this.ten = ten;
		this.email = email;
		this.diaChi = diaChi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	
}
