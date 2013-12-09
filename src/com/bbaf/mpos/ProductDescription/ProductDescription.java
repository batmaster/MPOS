package com.bbaf.mpos.ProductDescription;

import java.io.Serializable;

import com.bbaf.mpos.sale.SaleLineItem;

import android.util.Log;

/**
 * Class for product detail.
 * @author Atit Leelasuksan 5510546221
 * @version Dec 9, 2013
 */
public class ProductDescription implements Serializable {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this.getClass() == obj.getClass()) {
			ProductDescription product = (ProductDescription)obj;
			return id.equals(product.getId());
		}
		else if (SaleLineItem.class == obj.getClass()) {
			SaleLineItem line = (SaleLineItem)obj;
			return id.equals(line.getProductDescription().getId());
		}
		
		return false;
	}
	
	private int key;
	/** id of product. */
	private String id;
	/** name of product. */
	private String name;
	/** price of product. */
	private double price;
	/** cost of product. */
	private double cost;
	/** last date modified of product. */
	private String dateModified;
	
	/**
	 * Constructor of this class
	 * @param key refer to key
	 * @param id refer to id
	 * @param name refer to name
	 * @param price refer to price
	 * @param cost refer to cost
	 * @param dateModified refer to dateModified
	 */
	public ProductDescription(int key, String id, String name, double price, double cost, String dateModified) {
		this.key = key;
		this.id = id;
		this.name = name;
		this.price = price;
		this.cost = cost;
		this.dateModified = dateModified;
	}
	
	/**
	 * Constructor of this class
	 * @param id refer to id
	 * @param name refer to name
	 * @param price refer to price
	 * @param cost refer to cost
	 */
	public ProductDescription(String id, String name, double price, double cost) {
		this(0, id, name, price, cost, "");
	}
	
	/**
	 * return key
	 * @return key
	 */
	public int getKey() {
		return key;
	}
	
	/**
	 * return id
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * return name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * return price
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * return cost
	 * @return cost
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * return date type string
	 * @return date
	 */
	public String getDateModified() {
		return dateModified;
	}
}
