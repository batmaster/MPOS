package com.bbaf.mpos.sale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;
import android.widget.Toast;

import com.bbaf.mpos.DAO.InventoryDBHelper;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.inventory.Inventory;

/**
 * Class for sale function work with SaleLineItem.
 * @author Atit Leelasuksan 5510546221 , Rungroj Maipradit 5510546654
 * @version Oct 28, 2013
 */
public class Sale implements Serializable {
	
	/** List of SaleLineItem in this sale. */
	private ArrayList<SaleLineItem> lineOfItem;
	
	/** inventory to get or check product. */
	private Inventory inventory;
	
	/** date of sale. */
	private Date date;
	
	/**
	 * initialize sale with empty List of SaleLineItem.
	 */
	public Sale() {
		date = Calendar.getInstance().getTime();
		lineOfItem = new ArrayList<SaleLineItem>();
	}
	
	/**
	 * initialize sale with empty List of SaleLineItem and set date.
	 * @param sdate date
	 */
	public Sale(String sdate) {
		date = Calendar.getInstance().getTime();
		setDate(sdate);
		lineOfItem = new ArrayList<SaleLineItem>();
	}
	
	/**
	 * initialize sale with empty List of SaleLineItem and set inventory.
	 * @param inventory
	 */
	public Sale(Inventory inventory){
		this();
		this.inventory = inventory;
	}
	
	/**
	 * Add SaleLineItem to List if SaleLineItem doesn't exist in List.
	 * Add SaleLineItem instead if it exist in List.
	 * @param productDesc is product.
	 * @param quantity is quantity of product to add.
	 * @return true if add new SaleLineItem.
	 * 		false if add quantity instead.
	 */
	public boolean addSaleLineItem(ProductDescription productDesc,int quantity) {
		SaleLineItem line = checkItemInLine(new SaleLineItem(productDesc,quantity));
		if(!(line == null)) {
			line.addQuantity(quantity);
			return true;
		}
		lineOfItem.add(new SaleLineItem(productDesc, quantity));
		return true;
	}
	
	/**
	 * remove saleLineItem that match with productDesc
	 * @param productDesc productDescription that want to remove
	 * @return return true if can remove else false
	 */
	public boolean removeSaleLineItem(ProductDescription productDesc) {
		try {
			boolean canRemove = lineOfItem.remove(productDesc);
			Log.d("remove", canRemove + "");
			return canRemove;
		}
		catch (Exception ex) {
			return false;
		}
	}
	
	/**
	 * remove all saleLineItem.
	 * @return return true if can remove else false
	 */
	public boolean removeAllSaleLineItem() {
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
			total += sli.getTotal();
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
	public void decrease(String id,int quantity) {
		inventory.setQuantity(inventory.getProduct(id), inventory.getQuantity(id)-quantity);
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
	
	/**
	 * return date
	 * @return date
	 */
	public Date getDate(){
		return date;
	}
	
	/**
	 * set date
	 * @param s date
	 */
	private void setDate(String s){
		String[] temp = s.split(" ");
		date.setYear(Integer.parseInt(temp[0]));
		date.setMonth(Integer.parseInt(temp[1]));
		date.setDate(Integer.parseInt(temp[2]));
		date.setHours(Integer.parseInt(temp[3]));
		date.setMinutes(Integer.parseInt(temp[4]));
		date.setSeconds(Integer.parseInt(temp[5]));
	}
}
