package com.bbaf.mpos;

import java.io.Serializable;

public class ProductQuantity implements Serializable {
	
	private String id;
	private int quantity;
	
	public ProductQuantity(String id, int quantity) {
		this.id = id;
		this.quantity = quantity;
	}
	
	public String getId() {
		return id;
	}
	
	public int getQuantity() {
		return quantity;
	}

}
