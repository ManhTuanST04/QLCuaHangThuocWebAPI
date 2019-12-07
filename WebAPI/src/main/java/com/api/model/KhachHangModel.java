package com.api.model;

import javax.persistence.Column;

public class KhachHangModel {
private int id;

	private String DienThoai;
	
	private String MatKhau;

	private String Ten;

	private String Email;
	
	private String DiaChi;
	
	public KhachHangModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhachHangModel(int id, String dienThoai, String matKhau, String ten, String email, String diaChi) {
		super();
		this.id = id;
		DienThoai = dienThoai;
		MatKhau = matKhau;
		Ten = ten;
		Email = email;
		DiaChi = diaChi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDienThoai() {
		return DienThoai;
	}

	public void setDienThoai(String dienThoai) {
		DienThoai = dienThoai;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	
}
