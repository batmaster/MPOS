package com.bbaf.mpos.saleledger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.bbaf.mpos.DAO.InventoryDBHelper;
import com.bbaf.mpos.DAO.SaleLedgerDBHelper;
import com.bbaf.mpos.sale.Sale;

/**
 * Ledger to record sale.
 * Ledger will record all complete sale detail
 * e.g. date, total price, amount of product, list of product.
 * @author Atit Leelasuksan 5510546221
 * @version Dec 9, 2013
 */
public class Ledger {
	/** to set format of date. */
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy MM dd");
	
	private Calendar dateDaily;
	private Calendar dateWeek;
	private Calendar dateMonth;
	private SaleLedgerDBHelper dbDAO;
	
	private String date;
	private String week;
	private String month;
	
	/**
	 * if dateDaily,dateWeek,dateMonth are null initialize them.
	 */
	public Ledger() {
		dbDAO = SaleLedgerDBHelper.getInstance();
		if(dateDaily == null)
			dateDaily = Calendar.getInstance();
		if(dateWeek == null)
			dateWeek = Calendar.getInstance();
		if(dateMonth == null)
			dateMonth = Calendar.getInstance();
	}
	
	/**
	 * if dateDaily,dateWeek,dateMonth are null initialize them and set context.
	 * @param context of calling Activity
	 */
	public Ledger(Context context) {
		dbDAO = SaleLedgerDBHelper.getInstance(context);
		if(dateDaily == null)
			dateDaily = Calendar.getInstance();
		if(dateWeek == null)
			dateWeek = Calendar.getInstance();
		if(dateMonth == null)
			dateMonth = Calendar.getInstance();
	}
	
	/**
	 * Add sale in saleLedger connector.
	 * @param sale is sale to add.
	 */
	public void record(Sale sale){
		dbDAO.addSale(sale);
	}
	
	/**
	 * return all sale.
	 * @return all sale
	 */
	public ArrayList<SaleLedger> getAllSaleLedger() {
		return dbDAO.getAllSale();
	}
	
	/**
	 * return sale that time length equal 1 day.
	 * @return sale that time length equal 1 day
	 */
	public ArrayList<SaleLedger> getDaily() {
		String current = SDF.format(dateDaily.getTime());
		Calendar from = (Calendar) dateDaily.clone();
		from.add(Calendar.DATE,1);
		String to = SDF.format(from.getTime());
		date = String.format("Date : %s/%s/%s", current.split(" ")[2], current.split(" ")[1], current.split(" ")[0]);
		return dbDAO.getSale(current,to);
	}
	
	/**
	 * return sale that time length equal 1 week.
	 * @return sale that time length equal 1 week
	 */
	public ArrayList<SaleLedger> getWeek(){
		Calendar from = (Calendar) dateWeek.clone();
		Calendar temp = (Calendar) dateWeek.clone();
		temp.set(Calendar.DAY_OF_WEEK, 0);
		if(temp.get(Calendar.DATE) >= from.get(Calendar.DATE))
			temp.add(Calendar.DATE, -7);		
		temp.add(Calendar.DATE,1);
		String current = SDF.format(temp.getTime());
		from.add(Calendar.DATE,1);
		String to = SDF.format(from.getTime());
		Calendar temp2 = (Calendar)from.clone();
		temp2.add(Calendar.DATE, -1);
		String to2 = SDF.format(temp2.getTime());
		week = String.format("Week : %s/%s/%s ~ %s/%s/%s", current.split(" ")[2], current.split(" ")[1], current.split(" ")[0], to2.split(" ")[2], to2.split(" ")[1], to2.split(" ")[0]);
		return dbDAO.getSale(current,to);
	}
	
	/**
	 * return sale that time length equal 1 month.
	 * @return sale that time length equal 1 month
	 */
	public ArrayList<SaleLedger> getMonth(){
		Calendar from = (Calendar) dateMonth.clone();
		Calendar temp = (Calendar) dateMonth.clone();
		int mtemp = temp.get(Calendar.MONTH);
		temp.set(Calendar.DATE,1);
		temp.set(Calendar.MONTH, mtemp);
		from.add(Calendar.DATE,1);
		String current = SDF.format(temp.getTime());
		String to = SDF.format(from.getTime());
		month = String.format("Month : %s/%s", current.split(" ")[1], current.split(" ")[0]);
		return dbDAO.getSale(current,to);
	}
	
	/**
	 * decrease dateDaily by 1 day.
	 */
	public void prevDaily(){
		dateDaily.add(Calendar.DATE, -1);
	}

	/**
	 * increase dateDaily by 1 day.
	 */
	public void nextDaily(){
		dateDaily.add(Calendar.DATE, 1);
	}
	
	/**
	 * decrease dateWeek by 1 week.
	 */
	public void prevWeek(){
		dateWeek.set(Calendar.DAY_OF_WEEK, 0);
		dateWeek.add(Calendar.WEEK_OF_YEAR, -1);
	}
	
	/**
	 * decrease dateWeek by 1 week.
	 */
	public void nextWeek(){
		dateWeek.set(Calendar.DAY_OF_WEEK, 0);
		dateWeek.add(Calendar.WEEK_OF_YEAR, 1);
	}
	
	/**
	 * decrease dateMonth by 1 month.
	 */
	public void prevMonth(){
		int temp = dateMonth.get(Calendar.MONTH);
		dateMonth.set(Calendar.DATE, 0);
		dateMonth.set(Calendar.MONTH, temp);
	}

	/**
	 * increase dateMonth by 1 month.
	 */
	public void nextMonth(){
		int temp = dateMonth.get(Calendar.MONTH);
		dateMonth.set(Calendar.DATE, 0);
		dateMonth.set(Calendar.MONTH,temp+2);
	}
	/**
	 * return date as string format
	 * @return date as string
	 */
	public String getDateS() {
		return date;
	}
	
	/**
	 * return date range in week as string format
	 * @return date range in week as string
	 */
	public String getWeekS() {
		return week;
	}
	
	/**
	 * return date range in month as string format
	 * @return date range in month as string
	 */
	public String getMonthS() {
		return month;
	}
}
