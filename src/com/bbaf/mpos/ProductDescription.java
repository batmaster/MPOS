package com.bbaf.mpos;

public class ProductDescription {
	
	private int key;
	private int id;
	private String name;
	private double price;
	private double cost;
	private String dateModified;
	
	public ProductDescription(int key, int id, String name, double price, double cost, String dateModified) {
		this.key = key;
		this.id = id;
		this.name = name;
		this.price = price;
		this.cost = cost;
		this.dateModified = dateModified;
	}
	
	public ProductDescription(int id, String name, double price, double cost) {
		this(0, id, name, price, cost, "");
	}
	
	// maybe not used
	public ProductDescription(String key, String id, String name, String price, String cost, String dateModified) {
		this(Integer.parseInt(key), Integer.parseInt(id), name, Double.parseDouble(price), Double.parseDouble(cost), dateModified);
	}
	
	public int getKey() {
		return key;
	}
	
	public int getId() {
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
