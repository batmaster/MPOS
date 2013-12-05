package com.bbaf.mpos.FacadeController;

import java.util.ArrayList;

import android.content.Context;

import com.bbaf.mpos.DAO.InventoryDBHelper;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.inventory.Inventory;

public class Store {
	private Inventory inventory;
	private static Store store;
	private Store(){
		if(inventory == null)
			inventory = new Inventory();
	}
	
	/**
	 * return store if null create new one
	 * @return store
	 */
	public static Store getInstance() {
		if(store==null) store = new Store();
		return store;
	}
	
	/**
	 * Add product
	 * @param product productDescription of item
	 * @param quantity amount of item
	 * @return -1 if fail
	 */
	public long addProduct(ProductDescription product,int quantity){
		return inventory.addProduct(product,quantity);
	}

	/**
	 * Add quantity
	 * @param product productDescription of item
	 * @param quantity amount of item
	 */
	public void addQuantity(ProductDescription product, int quantity) {
		inventory.addQuantity(product, quantity);
	}
	
	/**
	 * Remove productDescription
	 * @param product productDescription of item
	 */
	public void removeProduct(ProductDescription product) {
		inventory.removeProduct(product);
	}
	
	/**
	 * get quantity
	 * @param id key that match with item
	 * @return quantity
	 */
	public int getQuantity(String id) {
		return inventory.getQuantity(id);
	}
	
	/**
	 * get productDescription
	 * @param id key that match with item
	 * @return productDescription
	 */
	public ProductDescription getProduct(String id) {
		return inventory.getProduct(id);
	}
	
	/**
	 * edit productDescription
	 * @param oldProduct the old one
	 * @param newProduct the new one
	 */
	public void editProduct(ProductDescription oldProduct,ProductDescription newProduct) {
		inventory.editProduct(oldProduct, newProduct);
	}
	
	/**
	 * edit quantity
	 * @param oldProduct the old one
	 * @param newProduct the new one
	 * @param quantity amount of item
	 */
	public void editQuantity(ProductDescription oldProduct, ProductDescription newProduct, int quantity) {
		inventory.editQuantity(oldProduct, newProduct, quantity);
	}
	
	/**
	 * get all productDescription
	 * @return list of productDescription
	 */
	public ArrayList<ProductDescription> getAllProduct() {
		return inventory.getAllProduct();
	}
	
	/**
	 * get inventory
	 * @return inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * initialize inventory database connector
	 * @param context of calling Activity
	 */
	public void initInventoryDB(Context context) {
		inventory.setDB(InventoryDBHelper.getInstance(context));
	}
}
