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
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String Name;
	
	@Column(name = "price")
	private int Price;
	
	@Column(name = "weight", nullable=true)
	private int Weight;
	
	@Column(name = "color", nullable=true)
	private String Color;

	@Column(name = "image", nullable=true)
	private String Image;

//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "chitietdonhang", 
//    joinColumns = { @JoinColumn(name = "idSP") }, 
//    inverseJoinColumns = {@JoinColumn(name = "idDH") })
//	private Collection<DonDatHang> lstDonDatHang;
	

	public Product() {
		
	}


	public Product(int id, String name, int price, int weight, String color, String image) {
		super();
		this.id = id;
		Name = name;
		Price = price;
		Weight = weight;
		Color = color;
		Image = image;
		//this.lstDonDatHang = lstDonDatHang;
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


	public int getWeight() {
		return Weight;
	}


	public void setWeight(int weight) {
		Weight = weight;
	}


	public String getColor() {
		return Color;
	}


	public void setColor(String color) {
		Color = color;
	}


	public String getImage() {
		return Image;
	}


	public void setImage(String image) {
		Image = image;
	}


//	public Collection<DonDatHang> getLstDonDatHang() {
//		return lstDonDatHang;
//	}
//
//
//	public void setLstDonDatHang(Collection<DonDatHang> lstDonDatHang) {
//		this.lstDonDatHang = lstDonDatHang;
//	}

	

	
	
	
}
