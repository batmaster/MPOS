package com.bbaf.mpos.sale;

public class Store {
	private Inventory inventory;
	private Store(){
		if(inventory == null)
			inventory = new Inventory();
	}
	
	public Inventory getInventory(){
		return inventory;
	}
}
