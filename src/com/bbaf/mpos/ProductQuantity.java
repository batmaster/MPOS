package com.bbaf.mpos;

public class ProductQuantity {
	
	private int id;
	private int quantity;
	
	public ProductQuantity(int id, int quantity) {
		this.id = id;
		this.quantity = quantity;
	}
	
	public ProductQuantity(String id, String quantity) {
		this(Integer.parseInt(id), Integer.parseInt(quantity));
	}
	
	public int getId() {
		return id;
	}
	
	public int getQuantity() {
		return quantity;
	}

}
