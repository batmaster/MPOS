package com.bbaf.mpos.sale;

import com.bbaf.mpos.ProductDescription;

/**
 * SaleLineItem to Show Detail of line item in sale
 * @author Atit Leelasuksan 5510546221 , Rungroj Maipradit 5510546654
 * @version Oct 28, 2013
 */
public class SaleLineItem {
	
	/** ProductDescription of this line item. */
	private ProductDescription productDesc;
	/** Quantity of product in this line item. */
	private int quantity;
	
	/**
	 * initialize SaleLineItem with ProductDescription.
	 * it will invoke other constructor with quantity of 1 instead.
	 * @param product is ProductDescription of this line item.
	 */
	public SaleLineItem(ProductDescription product) {
		this(product,1);
	}
	
	/**
	 * initialize SaleLineItem with ProductDescription and Quantity.
	 * if quantity less than 0 it will use 1 instead.
	 * @param product is ProductDescription of this line item.
	 * @param q is quantity of product in this line item.
	 */
	public SaleLineItem(ProductDescription product,int q) {
		productDesc = product;
		if(q>0) quantity = q;
		else quantity = 1;
	}
	
	/**
	 * Get ProductDescription of this line item.
	 * @return ProductDescription of this line item.
	 */
	public ProductDescription getProductDescription() {
		return productDesc;
	}
	
	/**
	 * Get Quantity of product in this line item.
	 * @return quantity of product.
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * add or subtract quantity of product in this line item. 
	 * it will add if amount>0 otherwise will subtract instead.
	 * @param amount to add or subtract.
	 * @return true if add otherwise subtract will return false.
	 */
	public boolean addQuantity(int amount) {
		quantity+=amount;
		if(amount>0) return true;
		else return false;
	}
	
	/**
	 * Calculate price of this item
	 * @return total item price
	 */
	public double GetTotal(){
		return quantity*productDesc.getPrice();
	}
}