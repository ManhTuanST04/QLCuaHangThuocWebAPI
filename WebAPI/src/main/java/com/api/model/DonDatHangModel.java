package com.api.model;

import java.sql.Date;

public class DonDatHangModel {
	//chu dau phai viet thuowng, that ngu si
	
	private int id;
	
	private int idKH;
	
	private Date ngayDat;
	
	private Date ngayXuat;

	private int tinhTrangDon;

	private int tongTien;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdKH() {
		return idKH;
	}

	public void setIdKH(int idKH) {
		this.idKH = idKH;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

	public Date getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(Date ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public int getTinhTrangDon() {
		return tinhTrangDon;
	}

	public void setTinhTrangDon(int tinhTrangDon) {
		this.tinhTrangDon = tinhTrangDon;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	public DonDatHangModel(int id, Date ngayDat, Date ngayXuat, int tinhTrangDon, int tongTien) {
		super();
		this.id = id;
		this.ngayDat = ngayDat;
		this.ngayXuat = ngayXuat;
		this.tinhTrangDon = tinhTrangDon;
		this.tongTien = tongTien;
	}

	
	
	
	
}
