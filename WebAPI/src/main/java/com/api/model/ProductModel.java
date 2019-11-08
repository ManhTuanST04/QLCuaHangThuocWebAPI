package com.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class ProductModel implements Serializable{
	public ProductModel() {
		
	}
	
	public ProductModel(int id, String name, int price, int weight, String color) {
		this.id = id;
		Name = name;
		Price = price;
		Weight = weight;
		Color = color;
	}

	private int id;
	
	private String Name;
	
	private int Price;
	
	private int Weight;
	
	private String Color;

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

}
