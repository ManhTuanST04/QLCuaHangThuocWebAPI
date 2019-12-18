package com.api.model;

public class BaoCaoDonHangModel {
	private int SoLuong;
	private int TongTien;
	
	public BaoCaoDonHangModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BaoCaoDonHangModel(int soLuong, int tongTien) {
		super();
		SoLuong = soLuong;
		TongTien = tongTien;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public int getTongTien() {
		return TongTien;
	}

	public void setTongTien(int tongTien) {
		TongTien = tongTien;
	}
}
