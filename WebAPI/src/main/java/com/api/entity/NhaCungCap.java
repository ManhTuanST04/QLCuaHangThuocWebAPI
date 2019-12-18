package com.api.entity;
import java.util.Collection;

import javax.persistence.Cacheable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "nhacungcap")
public class NhaCungCap {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "ten")
	private String Ten;
	
	@Column(name = "diachi")
	private String DiaChi;
	
	@Column(name = "dienthoai", nullable=true)
	private String DienThoai;
	
	@Column(name = "website", nullable=true)
	private String Website;

	@Column(name = "email", nullable=true)
	private String Email;
	
	@Column(name = "quocgia", nullable=true)
	private String QuocGia;

	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhaCungCap(int id, String ten, String diaChi, String dienThoai, String website, String email,
			String quocGia) {
		super();
		this.id = id;
		Ten = ten;
		DiaChi = diaChi;
		DienThoai = dienThoai;
		Website = website;
		Email = email;
		QuocGia = quocGia;
	}

	public NhaCungCap(String ten, String diaChi, String dienThoai, String website, String email,
			String quocGia) {
		super();
		Ten = ten;
		DiaChi = diaChi;
		DienThoai = dienThoai;
		Website = website;
		Email = email;
		QuocGia = quocGia;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getDienThoai() {
		return DienThoai;
	}

	public void setDienThoai(String dienThoai) {
		DienThoai = dienThoai;
	}

	public String getWebsite() {
		return Website;
	}

	public void setWebsite(String website) {
		Website = website;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getQuocGia() {
		return QuocGia;
	}

	public void setQuocGia(String quocGia) {
		QuocGia = quocGia;
	}

	
	
	
	
	
	
}
