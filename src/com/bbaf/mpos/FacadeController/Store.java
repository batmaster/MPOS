package com.bbaf.mpos.FacadeController;

import java.util.ArrayList;

import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.inventory.Inventory;

public class Store {
	private Inventory inventory;
	private static Store store;
	private Store(){
		if(inventory == null)
			inventory = new Inventory();
	}
	
	public static Store getInstance() {
		if(store==null) store = new Store();
		return store;
	}
	
	public long addProduct(ProductDescription product){
		return inventory.addProduct(product);
	}
	
	public void addQuantity(ProductDescription product, int quantity) {
		inventory.addQuantity(product, quantity);
	}
	
	public void removeProduct(ProductDescription product) {
		inventory.removeProduct(product);
	}
	
	public int getQuantity(String id) {
		return inventory.getQuantity(id);
	}
	
	public ProductDescription getProduct(String id) {
		return inventory.getProduct(id);
	}
	
	public void editProduct(ProductDescription oldProduct,ProductDescription newProduct) {
		inventory.editProduct(oldProduct, newProduct);
	}
	
	public void editQuantity(ProductDescription oldProduct, ProductDescription newProduct, int quantity) {
		inventory.editQuantity(oldProduct, newProduct, quantity);
	}
	
	public ArrayList<ProductDescription> getAllProduct() {
		return inventory.getAllProduct();
	}
}
