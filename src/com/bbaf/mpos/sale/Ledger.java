package com.bbaf.mpos.sale;

import java.util.ArrayList;
import java.util.Calendar;

public class Ledger {
	private ArrayList<Sale> listOfSale;
	private Calendar date;
	
	public Ledger(){
		date = Calendar.getInstance();
		listOfSale = new ArrayList<Sale>();
	}
	
	public void record(Sale sale){
		listOfSale.add(sale);
	}
	
	public ArrayList<Sale> getDaily(){
		ArrayList<Sale> ret = new ArrayList<Sale>();
		for(Sale sale : listOfSale){
			if(sale.getDate().get(Calendar.DATE) == date.get(Calendar.DATE))
				ret.add(sale);
		}
		return ret;
	}
	
	public ArrayList<Sale> getWeek(){
		ArrayList<Sale> ret = new ArrayList<Sale>();
		for(Sale sale : listOfSale){
			if(sale.getDate().get(Calendar.WEEK_OF_YEAR) == date.get(Calendar.WEEK_OF_YEAR))
				ret.add(sale);
		}
		return ret;
	}
	
	public ArrayList<Sale> getMonth(){
		ArrayList<Sale> ret = new ArrayList<Sale>();
		for(Sale sale : listOfSale){
			if(sale.getDate().get(Calendar.MONTH) == date.get(Calendar.MONTH))
				ret.add(sale);
		}
		return ret;
	}
}
