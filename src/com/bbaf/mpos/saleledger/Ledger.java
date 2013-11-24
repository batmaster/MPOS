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
	
	public ArrayList<Sale> getAllSaleLedger() {
		return dbDAO.getAllSale();
	}
	
	public ArrayList<Sale> getDaily() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH mm ss");
		String current = sdf.format(date);
		Calendar from = (Calendar) date.clone();
		from.set(Calendar.HOUR_OF_DAY, 0);
		from.set(Calendar.MINUTE, 0);
		from.set(Calendar.SECOND, 0);
		String to = sdf.format(from);
		return dbDAO.saleList(sdf,to);
	}
	
	public ArrayList<Sale> getWeek(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH mm ss");
		String current = sdf.format(date);
		Calendar from = (Calendar) date.clone();
		from.set(Calendar.HOUR_OF_DAY, 0);
		from.set(Calendar.MINUTE, 0);
		from.set(Calendar.SECOND, 0);
		if(date.get(Calendar.DAY_OF_WEEK) != 0){
			date.add(Calendar.DATE, -7);
		}
		String to = sdf.format(from);
		return dbDAO.saleList(sdf,to);
	}
	
	public ArrayList<Sale> getMonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH mm ss");
		String current = sdf.format(date);
		Calendar from = (Calendar) date.clone();
		from.set(Calendar.HOUR_OF_DAY, 0);
		from.set(Calendar.MINUTE, 0);
		from.set(Calendar.SECOND, 0);
		from.set(Calendar.DATE,1);
		String to = sdf.format(from);
		return dbDAO.saleList(sdf,to);
	}
	
	public void removeSale(Calendar calendar){
		dbDAO.removeSale(calendar);
	}
}
