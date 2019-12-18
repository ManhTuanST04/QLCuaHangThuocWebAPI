package com.api.model;

public class NhaCungCapModel {
	private int id;
	
	private String ten;

	private String diaChi;
	

	private String dienThoai;

	private String website;

	private String email;
	

	private String quocGia;


	public NhaCungCapModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public NhaCungCapModel(int id, String ten, String diaChi, String dienThoai, String website, String email,
			String quocGia) {
		super();
		this.id = id;
		this.ten = ten;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.website = website;
		this.email = email;
		this.quocGia = quocGia;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTen() {
		return ten;
	}


	public void setTen(String ten) {
		this.ten = ten;
	}


	public String getDiaChi() {
		return diaChi;
	}


	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}


	public String getDienThoai() {
		return dienThoai;
	}


	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getQuocGia() {
		return quocGia;
	}


	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}
	
	
}
