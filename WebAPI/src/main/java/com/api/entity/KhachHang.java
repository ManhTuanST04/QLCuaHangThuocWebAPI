package com.api.entity;
import java.util.Collection;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "khachhang")
public class KhachHang {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "dienthoai")
	private String DienThoai;
	
	@Column(name = "matkhau")
	private String MatKhau;
	
	@Column(name = "ten")
	private String Ten;
	
	@Column(name = "email")
	private String Email;
	
	@Column(name = "diachi")
	private String DiaChi;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khachhang")
	private Collection<DonDatHang> lstDDH;

	
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhachHang(int id, String dienThoai, String matKhau, String ten, String email, String diaChi,
			Collection<DonDatHang> lstDDH) {
		super();
		this.id = id;
		DienThoai = dienThoai;
		MatKhau = matKhau;
		Ten = ten;
		Email = email;
		DiaChi = diaChi;
		this.lstDDH = lstDDH;
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

	public Collection<DonDatHang> getLstDDH() {
		return lstDDH;
	}

	public void setLstDDH(Collection<DonDatHang> lstDDH) {
		this.lstDDH = lstDDH;
	}
	
	
}
