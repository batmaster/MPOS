package com.bbaf.mpos.sale;

public class Store {
	private Inventory inventory;
	private Register register;
	private Store(Register register){
		this.register = register;
		if(inventory == null)
			inventory = new Inventory();
	}
	
	public Inventory getInventory(){
		return inventory;
	}
}
