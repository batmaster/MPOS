package com.bbaf.mpos.saleledger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class SaleLedger implements Serializable {
	
	private String date;
	private double total;
	private ArrayList<ProductLedger> productLedgerList;
	
	public SaleLedger(String date, double total) {
		this.date = date;
		this.total = total;
		productLedgerList = new ArrayList<ProductLedger>();
	}
	
	public void addProductLedger(ProductLedger productLedger) {
		productLedgerList.add(productLedger);
	}
	
	public ArrayList<ProductLedger> getAllProductLedger() {
		return productLedgerList;
	}
	
	public String getDate() {
		return date;
	}

	public double getTotal() {
		return total;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setProductLedgerList(ArrayList<ProductLedger> productLedgerList) {
		this.productLedgerList = productLedgerList;
	}
	
	public String getDateX() {
		String[] x = date.split(" ");
		return String.format("%s-%s-%s", x[0], x[1], x[2]);
	}
	
	public String getTimeX() {
		String[] x = date.split(" ");
		return String.format("%s:%s:%s", x[3], x[4], x[5]);
	}
}
