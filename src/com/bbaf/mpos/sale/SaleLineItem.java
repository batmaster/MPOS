package com.bbaf.mpos.sale;

import java.io.Serializable;

import com.bbaf.mpos.ProductDescription.ProductDescription;

/**
 * SaleLineItem to Show Detail of line item in sale
 * @author Atit Leelasuksan 5510546221 , Rungroj Maipradit 5510546654
 * @version Oct 28, 2013
 */
public class SaleLineItem implements Serializable {
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this.getClass() == obj.getClass()) {
			SaleLineItem line = (SaleLineItem)obj;
			return productDesc.getId().equals(line.getProductDescription().getId());
		}
		else if (ProductDescription.class == obj.getClass()) {
			ProductDescription product = (ProductDescription)obj;
			return productDesc.getId().equals(product.getId());
		}
		
		return false;
	}

	/** ProductDescription of this line item. */
	private ProductDescription productDesc;
	/** Quantity of product in this line item. */
	private int quantity;
	
	private double unitPrice;
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
		unitPrice = product.getPrice();
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
	 * Calculate price of this item.
	 * @return total item price
	 */
	public double getTotal(){
		return quantity * unitPrice;
	}
	
	/**
	 * return unitPrice.
	 * @return unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}
	
	/**
	 * set unitPrice
	 * @param price that use for set unitPrice
	 */
	public void setUnitPrice(double price){
		this.unitPrice = price;
	}
}