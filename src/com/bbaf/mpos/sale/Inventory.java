package com.bbaf.mpos.sale;

import java.util.ArrayList;

import com.bbaf.mpos.ProductDescription;

public class Inventory {
	private ArrayList<ProductDescription> product;
	
	public Inventory(){
		product = new ArrayList<ProductDescription>();
	}
	
	public void addProduct(ProductDescription product){
		if(this.product.contains(product))
			return;
		this.product.add(product);
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
	
}
