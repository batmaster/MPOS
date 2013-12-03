package com.bbaf.mpos.saleledger;

import java.io.Serializable;

public class ProductLedger implements Serializable {
	
	private String id;
	private String name;
	private double unitPrice;
	private int quantity;
	
	public ProductLedger(String id, String name, double unitPrice, int quantity) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
