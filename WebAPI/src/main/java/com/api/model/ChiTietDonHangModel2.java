package com.api.model;

public class ChiTietDonHangModel2 {
	private int id;
	
	private String Name;
	
	private int Price;

	private String Image;
	
	private int SoLuong ;
	
	private int ThanhTien ;

	public ChiTietDonHangModel2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietDonHangModel2(int id, String name, int price, String image, int soLuong, int thanhTien) {
		super();
		this.id = id;
		Name = name;
		Price = price;
		Image = image;
		SoLuong = soLuong;
		ThanhTien = thanhTien;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public int getThanhTien() {
		return ThanhTien;
	}

	public void setThanhTien(int thanhTien) {
		ThanhTien = thanhTien;
	}
	
	
}
