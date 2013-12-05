package com.bbaf.mpos.saleledger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class SaleLedger implements Serializable {
	
	private String date;
	private double total;
	private ArrayList<ProductLedger> productLedgerList;
	
	/**
	 * Initialize list of productLedger and set date,total.
	 * @param date that use to set
	 * @param total that use to set
	 */
	public SaleLedger(String date, double total) {
		this.date = date;
		this.total = total;
		productLedgerList = new ArrayList<ProductLedger>();
	}
	
	/**
	 * add productLedger.
	 * @param productLedger that use to add.
	 */
	public void addProductLedger(ProductLedger productLedger) {
		productLedgerList.add(productLedger);
	}
	
	/**
	 * return all productLedger.
	 * @return all productLedger.
	 */
	public ArrayList<ProductLedger> getAllProductLedger() {
		return productLedgerList;
	}
	
	/**
	 * return date type string.
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * return total price.
	 * @return total price.
	 */
	public double getTotal() {
		return total;
	}
	
	/**
	 * set date
	 * @param date that use to set.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * set total
	 * @param total that use to set.
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * set list of ProductLedger.
	 * @param productLedgerList list of productLedger that use to set.
	 */
	public void setProductLedgerList(ArrayList<ProductLedger> productLedgerList) {
		this.productLedgerList = productLedgerList;
	}
	
	/**
	 * return date with new format.
	 * @return date 
	 */
	public String getDateX() {
		String[] x = date.split(" ");
		return String.format("%s-%s-%s", x[0], x[1], x[2]);
	}
	
	/**
	 * return time with new format.
	 * @return time
	 */
	public String getTimeX() {
		String[] x = date.split(" ");
		return String.format("%s:%s:%s", x[3], x[4], x[5]);
	}
}
