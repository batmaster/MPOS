package com.bbaf.mpos.sale;

import java.util.ArrayList;

/**
 * Class for sale function.
 * @author Atit Leelasuksan 5510546221
 * @version Oct 27, 2013
 */
public class Sale {
	
	/** List of SaleLineItem in this sale. */
	private ArrayList<SaleLineItem> lineOfItem;
	
	/**
	 * initialize sale with empty List of SaleLineItem.
	 */
	public Sale() {
		lineOfItem = new ArrayList<SaleLineItem>();
	}
	
	/**
	 * Add SaleLineItem to List if SaleLineItem doesn't exist in List.
	 * Add SaleLineItem instead if it exist in List.
	 * @param lineItem is SaleLineItem to add.
	 * @return true if add new SaleLineItem.
	 * 		false if add quantity instead.
	 */
	public boolean AddSaleLineItem(SaleLineItem lineItem) {
		SaleLineItem line = checkItemInLine(lineItem);
		if(!line.equals(null)) {
			line.addQuantity(lineItem.getQuantity());
			return false;
		}
		lineOfItem.add(lineItem);
		return true;
	}
	
	/**
	 * Check SaleLineItem in List.
	 * @param lineItem is SaleLineItem to check.
	 * @return SaleLineItem that match with parameter if found 
	 * 		otherwise return null.
	 */
	private SaleLineItem checkItemInLine(SaleLineItem lineItem) {
		for(int i = 0 ; i < lineOfItem.size() ; i++) {
			if(lineItem.getProductDescription().equals(lineOfItem.get(i).getProductDescription())) return lineOfItem.get(i);
		}
		return null;
	}
	
}
