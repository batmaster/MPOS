package com.bbaf.mpos;

import java.io.Serializable;

public class ProductQuantity implements Serializable {
	
	private int id;
	private int quantity;
	
	public ProductQuantity(int id, int quantity) {
		this.id = id;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	
	public int getQuantity() {
		return quantity;
	}

}
