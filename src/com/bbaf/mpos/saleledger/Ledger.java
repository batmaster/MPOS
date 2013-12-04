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
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy MM dd");
	
	private Calendar dateDaily;
	private Calendar dateWeek;
	private Calendar dateMonth;
	private SaleLedgerDBHepler dbDAO;
	
	private String date;
	private String week;
	private String month;
	
	public Ledger() {
		dbDAO = SaleLedgerDBHepler.getInstance();
		if(dateDaily == null)
			dateDaily = Calendar.getInstance();
		if(dateWeek == null)
			dateWeek = Calendar.getInstance();
		if(dateMonth == null)
			dateMonth = Calendar.getInstance();
	}
	
	public Ledger(Context context) {
		dbDAO = SaleLedgerDBHepler.getInstance(context);
		if(dateDaily == null)
			dateDaily = Calendar.getInstance();
		if(dateWeek == null)
			dateWeek = Calendar.getInstance();
		if(dateMonth == null)
			dateMonth = Calendar.getInstance();
	}
	
	public void record(Sale sale){
		dbDAO.addSale(sale);
	}
	
	public ArrayList<SaleLedger> getAllSaleLedger() {
		return dbDAO.getAllSale();
	}
	
	public ArrayList<SaleLedger> getDaily() {
		String current = SDF.format(dateDaily.getTime());
		Calendar from = (Calendar) dateDaily.clone();
		from.add(Calendar.DATE,1);
		String to = SDF.format(from.getTime());
		date = current;
		return dbDAO.getSale(current,to);
	}
	
	public ArrayList<SaleLedger> getWeek(){
		Calendar from = (Calendar) dateMonth.clone();
		Calendar temp = (Calendar) dateMonth.clone();
		temp.set(Calendar.DAY_OF_WEEK, 0);
		if(temp.get(Calendar.DATE) > from.get(Calendar.DATE))
			temp.add(Calendar.DATE, -7);
		String current = SDF.format(temp.getTime());
		from.add(Calendar.DATE,1);
		String to = SDF.format(from.getTime());
		week = String.format("%s to %s", current, to);
		return dbDAO.getSale(current,to);
	}
	
	public ArrayList<SaleLedger> getMonth(){
		Calendar from = (Calendar) dateMonth.clone();
		Calendar temp = (Calendar) dateMonth.clone();
		temp.set(Calendar.DATE, 1);
		String current = SDF.format(temp.getTime());
		from.add(Calendar.DATE,1);
		String to = SDF.format(from.getTime());
		month = String.format("%s %s", current.split(" ")[1], current.split(" ")[0]);
		return dbDAO.getSale(current,to);
	}
	
	public void prevDaily(){
		dateDaily.add(Calendar.DATE, -1);
	}
	
	public void nextDaily(){
		dateDaily.add(Calendar.DATE, 1);
	}
	
	public void prevWeek(){
		dateWeek.add(Calendar.WEEK_OF_YEAR, -1);
	}
	
	public void nextWeek(){
		dateWeek.add(Calendar.WEEK_OF_YEAR, 1);
	}
	
	public void prevMonth(){
		dateMonth.add(Calendar.MONTH, -1);
	}
	
	public void nextMonth(){
		dateMonth.add(Calendar.MONTH, 1);
	}
	
	public String getDateS() {
		return date;
	}
	
	public String getWeekS() {
		return week;
	}
	
	public String getMonthS() {
		return month;
	}
}
