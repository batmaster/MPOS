package com.bbaf.mpos.saleledger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.bbaf.mpos.DAO.InventoryDBHelper;
import com.bbaf.mpos.DAO.SaleLedgerDBHepler;
import com.bbaf.mpos.sale.Sale;

public class Ledger {
	private Calendar date;
	
	private SaleLedgerDBHepler dbDAO;
	
	public Ledger() {
		dbDAO = SaleLedgerDBHepler.getInstance();
		date = Calendar.getInstance();
	}
	
	public Ledger(Context context) {
		dbDAO = SaleLedgerDBHepler.getInstance(context);
		date = Calendar.getInstance();
	}
	
	public void record(Sale sale){
		dbDAO.addSale(sale);
	}
	
	public ArrayList<SaleLedger> getAllSaleLedger() {
		return dbDAO.getAllSale();
	}
	
	public ArrayList<SaleLedger> getDaily() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
		String current = sdf.format(date.getTime());
		Calendar from = (Calendar) date.clone();
		from.add(Calendar.DATE,1);
		String to = sdf.format(from.getTime());
		return dbDAO.getSale(current,to);
	}
	
	public ArrayList<SaleLedger> getWeek(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
		Calendar from = (Calendar) date.clone();
		Calendar temp = (Calendar) date.clone();
		temp.set(Calendar.DAY_OF_WEEK, 0);
		if(temp.get(Calendar.DATE) > from.get(Calendar.DATE))
			temp.add(Calendar.DATE, -7);
		String current = sdf.format(temp.getTime());
		from.add(Calendar.DATE,1);
		String to = sdf.format(from.getTime());
		return dbDAO.getSale(current,to);
	}
	
	public ArrayList<SaleLedger> getMonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
		Calendar from = (Calendar) date.clone();
		Calendar temp = (Calendar) date.clone();
		temp.set(Calendar.DATE, 1);
		String current = sdf.format(temp.getTime());
		from.add(Calendar.DATE,1);
		String to = sdf.format(from.getTime());
		return dbDAO.getSale(current,to);
	}
	
	public void removeSale(Calendar calendar){
		dbDAO.removeSale(calendar);
	}
}
