package com.bbaf.mpos.sale;

import java.util.ArrayList;

import com.bbaf.mpos.ProductDescription;

/**
 * Register is controlled for sale.
 * @author Atit Leelasuksan 5510546221
 * @version Oct 29, 2013
 */
public class Register {
	
	/** sale for register. */
	private Sale sale;
	
	/**
	 * initialize.
	 */
	public Register() {}
	
	/**
	 * create new sale.
	 */
	public void startSale() {
		sale = new Sale();
	}
	
	/**
	 * get total of sale.
	 * @return total of current sale.
	 */
	public double getTotal() {
		if(!sale.equals(null)) return sale.getTotal();
		return 0;
	}
	
	/**
	 * add item to sale.
	 * @param productDesc is item to add.
	 * @param quantity of item to add.
	 * @return 
	 */
	public boolean addItem(ProductDescription productDesc,int quantity) {
		if(!sale.equals(null)) {
			sale.AddSaleLineItem(productDesc,quantity);
			return true;
		}
		return false;
	}
	
	/**
	 * remove item in sale.
	 * @param productDesc is item to add.
	 * @return 
	 */
	public boolean removeItem(ProductDescription productDesc) {
		if(!sale.equals(null)) {
			sale.RemoveSaleLineItem(productDesc);
			return true;
		}
		return false;
	}
	
	/**
	 * remove all item in sale.
	 * @param productDesc is item to add.
	 * @return 
	 */
	public boolean removeAllItem() {
		if(!sale.equals(null)) {
			sale.RemoveAllSaleLineItem();
			return true;
		}
		return false;
	}
	
	/**
	 * get all list of item in sale.
	 * @return all list of item in current sale.
	 */
	public ArrayList<SaleLineItem> getAllSaleLineItemList() {
		if(!sale.equals(null))
			return sale.getAllList();
		return null;
	}
	
	/**
	 * get list of item in sale.
	 * @return list of item in current sale.
	 */
	public SaleLineItem getSaleLineItemList(ProductDescription product) {
		if(!sale.equals(null))
			return sale.getList(product);
		return null;
	}
	
	/**
	 * remove current sale.
	 */
	public void endSale() {
		if(!sale.equals(null)) sale = null;
	}
}
