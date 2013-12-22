package com.bbaf.mpos.ProductDescription;

/**
 * Class to represent quantity of product that have same id.
 * @author Atit Leelasuksan 5510546221
 *
 */
public class ProductQuantity {

	/** id of product. */
	private String id;
	
	/** quantity of product. */
	private int quantity;
	
	/**
	 * Constructor to initialize with id and quantity.
	 * @param id of product.
	 * @param quan quantity of product.
	 */
	public ProductQuantity(String id,int quan) {
		this.id = id;
		quantity = quan;
	}
}
