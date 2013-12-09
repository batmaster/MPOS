package com.bbaf.mpos.saleledger;

import java.io.Serializable;

/**
 * ProductLedger is class for product that use in SaleLedger.
 * @author Atit Leelasuksan 5510546221
 * @version Dec 9, 2013
 */
public class ProductLedger implements Serializable {
	
	/** id of product. */
	private String id;
	/** name of product. */
	private String name;
	/** unit price of product. */
	private double unitPrice;
	/** quantity of product. */
	private int quantity;
	
	/**
	 * Constructor of this class 
	 * @param id set id
	 * @param name set name
	 * @param unitPrice set unitPrice
	 * @param quantity set quantity
	 */
	public ProductLedger(String id, String name, double unitPrice, int quantity) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
	
	/**
	 * return id.
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * return name.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * return unitPrice.
	 * @return unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}
	
	/**
	 * return quantity.
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * set id
	 * @param id that use to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * set name.
	 * @param name that use to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * set unitPrice
	 * @param unitPrice that use to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/**
	 * set quantity
	 * @param quantity that use to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
