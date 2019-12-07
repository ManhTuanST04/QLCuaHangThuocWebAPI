package com.api.model;

public class ChiTietDonHangModel {
	 private int idDH ;
	 private int idSP ;
	 private int soLuong ;
	 private int tongTien ;
	 
	 
	public ChiTietDonHangModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ChiTietDonHangModel(int idDH, int idSP, int soLuong, int tongTien) {
		super();
		this.idDH = idDH;
		this.idSP = idSP;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}


	public int getIdDH() {
		return idDH;
	}


	public void setIdDH(int idDH) {
		this.idDH = idDH;
	}


	public int getIdSP() {
		return idSP;
	}


	public void setIdSP(int idSP) {
		this.idSP = idSP;
	}


	public int getSoLuong() {
		return soLuong;
	}


	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}


	public int getTongTien() {
		return tongTien;
	}


	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}


	
	 
	 
}
