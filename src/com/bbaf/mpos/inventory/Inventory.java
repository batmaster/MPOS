package com.bbaf.mpos.inventory;

import java.io.Serializable;
import java.util.ArrayList;

import DAO.InventoryDBHelper;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.bbaf.mpos.ProductDescription.ProductDescription;

public class Inventory implements Serializable {
	private ArrayList<ProductDescription> product;
	private InventoryDBHelper dbDAO;
	
	public Inventory(){
		product = new ArrayList<ProductDescription>();
		dbDAO = InventoryDBHelper.getInstance();
	}
	
	public void setDB(InventoryDBHelper db) {
		dbDAO = db;
	}
	
	public long addProduct(ProductDescription product,int quantity){
		return dbDAO.addProduct(product,quantity);
	}
	
	public void addQuantity(ProductDescription product, int quantity) {
		dbDAO.addQuantity(product, quantity);
	}
	
	public ArrayList<ProductDescription> getProducts(){
		return product;
	}
	
	public ProductDescription search(String id){
		for(ProductDescription p : product){
			if(p.getId().equals(id))
				return p;
		}
		return null;
	}
	
	public long setQuantity(ProductDescription product, int quantity) {
		return dbDAO.setQuantity(product, quantity);
	}

	public ProductDescription getProduct(String id) {
		return dbDAO.getProduct(id);
	}

	public int getQuantity(String id) {
		return dbDAO.getQuantity(id);
	}

	public void editProduct(ProductDescription oldProduct,
			ProductDescription newProduct) {
		dbDAO.editProduct(oldProduct, newProduct);
	}

	public void editQuantity(ProductDescription oldProduct,
			ProductDescription newProduct, int quantity) {
		dbDAO.editQuantity(oldProduct, newProduct, quantity);
	}
	
	public ArrayList<ProductDescription> getProductBySomething(String something) {
		return dbDAO.getProductBySomething(something);
	}
	
	public void removeProduct(ProductDescription product) {
		dbDAO.removeProduct(product);
	}
	
	public ArrayList<ProductDescription> getAllProduct() {
		return dbDAO.getAllProduct();
	}
}
