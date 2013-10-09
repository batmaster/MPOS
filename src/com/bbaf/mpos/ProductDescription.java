package com.bbaf.mpos;

import java.io.Serializable;

public class ProductDescription implements Serializable {
	
	private int key;
	private String id;
	private String name;
	private double price;
	private double cost;
	private String dateModified;
	
	public ProductDescription(int key, String id, String name, double price, double cost, String dateModified) {
		this.key = key;
		this.id = id;
		this.name = name;
		this.price = price;
		this.cost = cost;
		this.dateModified = dateModified;
	}
	
	public ProductDescription(String id, String name, double price, double cost) {
		this(0, id, name, price, cost, "");
	}
	
	public int getKey() {
		return key;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String getDateModified() {
		return dateModified;
	}
}
