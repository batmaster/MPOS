package com.bbaf.mpos.sale;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;
import android.widget.Toast;

import com.bbaf.mpos.ProductDescription;
import com.bbaf.mpos.inventory.InventoryDBHelper;

/**
 * Class for sale function.
 * @author Atit Leelasuksan 5510546221 , Rungroj Maipradit 5510546654
 * @version Oct 28, 2013
 */
public class Sale implements Serializable {
	
	/** List of SaleLineItem in this sale. */
	private ArrayList<SaleLineItem> lineOfItem;
	
	private InventoryDBHelper dbhelper;
	/**
	 * initialize sale with empty List of SaleLineItem.
	 */
	public Sale() {
		lineOfItem = new ArrayList<SaleLineItem>();
	}
	
	public Sale(InventoryDBHelper inventory){
		this();
		this.dbhelper = inventory;
	}
	
	/**
	 * Add SaleLineItem to List if SaleLineItem doesn't exist in List.
	 * Add SaleLineItem instead if it exist in List.
	 * @param productDesc is product.
	 * @param quantity is quantity of product to add.
	 * @return true if add new SaleLineItem.
	 * 		false if add quantity instead.
	 */
	public boolean AddSaleLineItem(ProductDescription productDesc,int quantity) {
		SaleLineItem line = checkItemInLine(new SaleLineItem(productDesc,quantity));
		if(!(line == null)) {
			line.addQuantity(quantity);
			return true;
		}
		lineOfItem.add(new SaleLineItem(productDesc, quantity));
		return true;
	}
	
	public boolean RemoveSaleLineItem(ProductDescription productDesc) {
		try {
			lineOfItem.remove(productDesc);
		}
		catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	public boolean RemoveAllSaleLineItem() {
		try {
			lineOfItem.clear();
		}
		catch (Exception ex) {
			return false;
		}
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
			if(lineItem.getProductDescription().equals(lineOfItem.get(i).getProductDescription()))
				return lineOfItem.get(i);
		}
		return null;
	}
	
	/**
	 * Calculate total price.
	 * @return total price
	 */
	public double getTotal(){
		double total = 0;
		for(SaleLineItem sli : lineOfItem){
			total += sli.GetTotal();
		}
		return total;
	}
	
	/**
	 * return change that calculate from money and total price
	 * @param money customer pay
	 * @return money minus total price
	 */
	public double change(double money){
		return money - getTotal();
	}
	
	/**
	 * decrease quantity that match which id.
	 * @param id that match which inventory.
	 * @param quantity amount of quantity that decrease from total quantity in inventory.
	 */
	public void decrease(String id,int quantity){
		dbhelper.setQuantity(dbhelper.getProduct(id), dbhelper.getQuantity(id)-quantity);
	}
	
	/**
	 * return all list of sale line item.
	 * @return all list of sale line item.
	 */
	public ArrayList<SaleLineItem> getAllList() {
		return lineOfItem;
	}
	
	/**
	 * return list of sale line item.
	 * @return list of sale line item.
	 */
	public SaleLineItem getList(ProductDescription product) {
		for (int i = 0; i < lineOfItem.size(); i++) {
			if (lineOfItem.get(i).getProductDescription().equals(product))
				return lineOfItem.get(i);
		}
		return null;
	}
}
