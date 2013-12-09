package com.bbaf.mpos.FacadeController;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;
import android.widget.Toast;

import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.inventory.Inventory;
import com.bbaf.mpos.sale.Sale;
import com.bbaf.mpos.sale.SaleLineItem;
import com.bbaf.mpos.saleledger.Ledger;

/**
 * Register is controlled for sale.
 * @author Atit Leelasuksan 5510546221 , Rungroj Maipradit 5510546654
 * @version Oct 29, 2013
 */
public class Register implements Serializable {
	
	/** sale for register. */
	private Sale sale;
	
	/** Inventory for register. */
	private Inventory inventory;
	/** Ledger for register. */
	private Ledger ledger;
	
	/** instance of register to be singleton. */
	private static Register register;
	
	/**
	 * initialize.
	 */
	private Register(Inventory inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * get register if null create new one
	 * @param inventory set Inventory
	 * @return register
	 */
	public static Register getInstance(Inventory inventory) {
		if(register==null) register = new Register(inventory);
		return register;
	}
	
	/**
	 * get register if null create new one
	 * @return register
	 */
	public static Register getInstance() {
		if(register==null) register = new Register(new Inventory());
		return register;
	}
	
	/**
	 * create new sale.
	 */
	public void startSale() {
		if(sale==null)
			sale = new Sale(inventory);
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
	 * @return true if add complete otherwise false.
	 */
	public boolean addItem(ProductDescription productDesc,int quantity) {
		if(!sale.equals(null)) {
			sale.addSaleLineItem(productDesc,quantity);
			return true;
		}
		return false;
	}
	
	/**
	 * remove item in sale.
	 * @param productDesc is item to add.
	 * @return true if remove complete otherwise false.
	 */
	public boolean removeItem(ProductDescription productDesc) {
		if(!sale.equals(null)) {
			sale.removeSaleLineItem(productDesc);
			return true;
		}
		return false;
	}
	
	/**
	 * remove all item in sale.
	 * @param productDesc is item to add.
	 * @return true if remove complete otherwise false.
	 */
	public boolean removeAllItem() {
		if(!sale.equals(null)) {
			sale.removeAllSaleLineItem();
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
	
	/**
	 * get sale status.
	 * @return true if sale not null otherwise false.
	 */
	public boolean isSale() {
		return !(sale==null);
	}
	
	/**
	 * get inventory.
	 * @return inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * Decrease amount of item that match with this id.
	 * @param id key that match with item
	 * @param quantity amount of item
	 */
	public void decrease(String id,int quantity) {
		inventory.setQuantity(inventory.getProduct(id), inventory.getQuantity(id) - quantity);
	}
	
	/**
	 * get sale
	 * @return sale
	 */
	public Sale getSale() {
		return sale;	
	}
	
	/**
	 * set ledger
	 * @param ledger set Ledger
	 */
	public void setLedger(Ledger ledger) {
		this.ledger = ledger;
	}
	
	/**
	 * get ledger
	 * @return ledger
	 */
	public Ledger getLedger() {
		return ledger;
	}
	
	/**
	 * return change
	 * @param input amount of money that get paid
	 * @return change
	 */
	public double change(double input){
		return input - getTotal();
	}
}
