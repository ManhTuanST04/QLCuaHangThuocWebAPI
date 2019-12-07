package com.api.entity;

import java.util.Collection;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "dondathang")
public class DonDatHang {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "ngaydat")
	private Date NgayDat;

	@Column(name = "ngayxuat")
	private Date NgayXuat;
	
	@Column(name = "tinhtrangdon")
	private int TinhTrangDon;

	@Column(name = "tongtien")
	private int TongTien;
	
	//Bảng chitietdonhang
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "chitietdonhang", 
	    joinColumns = { @JoinColumn(name = "idDH") }, 
	    inverseJoinColumns = {@JoinColumn(name = "idSP") })
	private Collection<Product> lstSanPham;
	
//	//Noi voi chi tiet don hang
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chitietdonhang")
//	private Collection<ChiTietDonHang> lstCTDH;
//	
	@ManyToOne
	@JoinColumn(name = "idKH", nullable = false)
	private KhachHang khachhang;

	public void AddSanPham(Product pro) {
		lstSanPham.add(pro);
	}
	
	
	public DonDatHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DonDatHang(int id, Date ngayDat, Date ngayXuat, int tinhTrangDon, int tongTien,
			Collection<Product> lstSanPham, KhachHang khachhang) {
		super();
		this.id = id;
		NgayDat = ngayDat;
		NgayXuat = ngayXuat;
		TinhTrangDon = tinhTrangDon;
		TongTien = tongTien;
		this.lstSanPham = lstSanPham;
		this.khachhang = khachhang;
	}
	
	public DonDatHang(int id, Date ngayDat, Date ngayXuat, int tinhTrangDon, int tongTien, KhachHang khachhang) {
		super();
		this.id = id;
		NgayDat = ngayDat;
		NgayXuat = ngayXuat;
		TinhTrangDon = tinhTrangDon;
		TongTien = tongTien;
		this.khachhang = khachhang;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNgayDat() {
		return NgayDat;
	}

	public void setNgayDat(Date ngayDat) {
		NgayDat = ngayDat;
	}

	public Date getNgayXuat() {
		return NgayXuat;
	}

	public void setNgayXuat(Date ngayXuat) {
		NgayXuat = ngayXuat;
	}

	public int getTinhTrangDon() {
		return TinhTrangDon;
	}

	public void setTinhTrangDon(int tinhTrangDon) {
		TinhTrangDon = tinhTrangDon;
	}

	public Collection<Product> getLstSanPham() {
		return lstSanPham;
	}

	public void setLstSanPham(Collection<Product> lstSanPham) {
		this.lstSanPham = lstSanPham;
	}

	public KhachHang getKhachhang() {
		return khachhang;
	}

	public void setKhachhang(KhachHang khachhang) {
		this.khachhang = khachhang;
	}


	public int getTongTien() {
		return TongTien;
	}


	public void setTongTien(int tongTien) {
		TongTien = tongTien;
	}

	
	

	
	
	
}
