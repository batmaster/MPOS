package com.bbaf.mpos.inventory;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.bbaf.mpos.DAO.InventoryDBHelper;
import com.bbaf.mpos.ProductDescription.ProductDescription;

public class Inventory implements Serializable {
	private ArrayList<ProductDescription> product;
	private InventoryDBHelper dbDAO;
	/**
	 * Constructor of inventory.
	 * Initialize attribute of this class.
	 */
	public Inventory(){
		product = new ArrayList<ProductDescription>();
		dbDAO = InventoryDBHelper.getInstance();
	}
	
	/**
	 * Set Inventory database connector
	 * @param db set Inventory database connector
	 */
	public void setDB(InventoryDBHelper db) {
		dbDAO = db;
	}
	
	/**
	 * Add productDescription
	 * @param product is item to add.
	 * @param quantity of item to add.
	 * @return
	 */
	public long addProduct(ProductDescription product,int quantity){
		return dbDAO.addProduct(product,quantity);
	}
	
	/**
	 * increase quantity.
	 * @param product is item to add.
	 * @param quantity of item to add.
	 */
	public void addQuantity(ProductDescription product, int quantity) {
		dbDAO.addQuantity(product, quantity);
	}
	
	/**
	 * return all product descriptions.
	 * @return all product descriptions
	 */
	public ArrayList<ProductDescription> getProducts(){
		return product;
	}
	
	/**
	 * Find productDescription that match with id
	 * @param id is productDescription to find.
	 * @return productDescription
	 */
	public ProductDescription search(String id){
		for(ProductDescription p : product){
			if(p.getId().equals(id))
				return p;
		}
		return null;
	}
	
	/**
	 * set quantity
	 * @param product is item to add.
	 * @param quantity of item to add.
	 * @return
	 */
	public long setQuantity(ProductDescription product, int quantity) {
		return dbDAO.setQuantity(product, quantity);
	}

	/**
	 * get productDescription that match with id.
	 * @param id of that item
	 * @return productDescription
	 */
	public ProductDescription getProduct(String id) {
		return dbDAO.getProduct(id);
	}

	/**
	 * get quantity.
	 * @param id of that item
	 * @return quantity
	 */
	public int getQuantity(String id) {
		return dbDAO.getQuantity(id);
	}

	/**
	 * edit productDescription
	 * @param oldProduct the old one
	 * @param newProduct the new one
	 */
	public void editProduct(ProductDescription oldProduct,
			ProductDescription newProduct) {
		dbDAO.editProduct(oldProduct, newProduct);
	}

	/**
	 * edit quantity
	 * @param oldProduct the old one
	 * @param newProduct the new one
	 * @param quantity amount of item
	 */
	public void editQuantity(ProductDescription oldProduct,
			ProductDescription newProduct, int quantity) {
		dbDAO.editQuantity(oldProduct, newProduct, quantity);
	}
	
	public ArrayList<ProductDescription> getProductBySomething(String something) {
		return dbDAO.getProductBySomething(something);
	}
	
	/**
	 * remove productDescription
	 * @param product that want to remove
	 */
	public void removeProduct(ProductDescription product) {
		dbDAO.removeProduct(product);
	}
	
	/**
	 * return all productDescription
	 * @return all productDescription
	 */
	public ArrayList<ProductDescription> getAllProduct() {
		return dbDAO.getAllProduct();
	}
}
