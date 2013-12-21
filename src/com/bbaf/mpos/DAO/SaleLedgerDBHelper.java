package com.bbaf.mpos.DAO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.sale.Sale;
import com.bbaf.mpos.sale.SaleLineItem;
import com.bbaf.mpos.saleledger.ProductLedger;
import com.bbaf.mpos.saleledger.SaleLedger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * SaleLedger DAO, for contact between java object and database.
 * @author Poramet Homprakob 5510546077
 */
public class SaleLedgerDBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "bbafmpos2.db";
	private static final String TABLE_SALE_LEDGER = "saleledger";
	private static final String TABLE_PRODUCT_LEDGER = "productledger";
	private static final int DATABASE_VERSION = 1;
	private static SaleLedgerDBHelper dao;

	private SaleLedgerDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Get SaleLedgerDBHelper object by initializing context.
	 * Only for first using SaleLedgerDBHelper.
	 * @param context context of calling activity
	 * @return SaleLedgerDBHelper object
	 */
	public static SaleLedgerDBHelper getInstance(Context context) {
		if (dao == null)
			dao = new SaleLedgerDBHelper(context);
		return dao;
	}

	/**
	 * Get SaleLedgerDBHelper object.
	 * @return SaleLedgerDBHelper object
	 */
	public static SaleLedgerDBHelper getInstance() {
		return dao;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// date text yyyy-MM-dd HH:mm:ss
		String sql = String
				.format("CREATE TABLE %s (_key INTEGER PRIMARY KEY, Date TEXT, TotalPrice DOUBLE);",
						TABLE_SALE_LEDGER);
		db.execSQL(sql);
		sql = String
				.format("CREATE TABLE %s (_key INTEGER PRIMARY KEY, Date TEXT, ProductId TEXT, ProductName TEXT, UnitPrice DOUBLE, Price DOUBLE, Quantity INTEGER);",
						TABLE_PRODUCT_LEDGER);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "USE TABLE IF EXISTS " + TABLE_SALE_LEDGER;
		db.execSQL(sql);
		sql = "USE TABLE IF EXISTS " + TABLE_PRODUCT_LEDGER;
		db.execSQL(sql);
	}

	/**
	 * Add Sale description to database.
	 * @param sale Sale object
	 * @return row of added sale description in database, -1 if failed
	 */
	public long addSale(Sale sale) {
		
		ArrayList<SaleLineItem> lines = sale.getAllList();
		
		// date text yyyy MM dd HH mm ss
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH mm ss");
		Date a = sale.getDate();
		String current = sdf.format(a);
		
		/** add Sale Ledger **/
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues value = new ContentValues();
			value.put("Date", current);
			value.put("TotalPrice", sale.getTotal());
			long row = db.insert(TABLE_SALE_LEDGER, null, value);

			db.close();
			Log.d("add sale", "add SL r:" + row + " " + value.toString());
			
		} catch (Exception e) {
			return -1;
		}
		
		/** looping add Product Ledger **/		
		try {
			SQLiteDatabase db = this.getWritableDatabase();

			for (int i = 0; i < lines.size(); i++) {
				SaleLineItem line = lines.get(i);
				
				ContentValues value = new ContentValues();
				value.put("Date", current);
				value.put("ProductId",line.getProductDescription().getId());
				value.put("ProductName", line.getProductDescription().getName());
				value.put("UnitPrice", line.getUnitPrice());
				value.put("Price", line.getTotal());
				value.put("Quantity", line.getQuantity());
				long row = db.insert(TABLE_PRODUCT_LEDGER, null, value);
				
				Log.d("add sale", "add PL r:" + row + " " + i + "/" + lines.size() + " " + value.toString());
			}
			
			db.close();

		} catch (Exception e) {
			return -2;
		}
		
		return 0;
	}
	
	/**
	 * Get all Sale in database.
	 * @return list of all sale in database
	 */
	public ArrayList<SaleLedger> getAllSale() {
		ArrayList<SaleLedger> saleList = new ArrayList<SaleLedger>();
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			
			/** Get all Sale Ledger **/
			String strSQL = "SELECT  * FROM " + TABLE_SALE_LEDGER;
			Cursor cursorSL = db.rawQuery(strSQL, null);
			if (cursorSL != null) {
				if (cursorSL.moveToFirst()) {
					do {
						/**
						 * 0 = _key 1 = Date 2 = Total
						 */
						SaleLedger sl = new SaleLedger(cursorSL.getString(1), cursorSL.getDouble(2));
						String date = cursorSL.getString(0);
						
							/** Get all SaleLineItem for that Sale Ledger **/
							Cursor cursorPL = db.query(TABLE_PRODUCT_LEDGER, new String[] { "*" },
									"Date=?", new String[] { date }, null,
									null, null, null);
	
							if (cursorPL != null) {
								if (cursorPL.moveToFirst()) {
									do {
										/**
										 * 0 = _key 1 = Date 2 = ProductId 3 = ProductName 4 = UnitPrice
										 * 5 = Price 6 = Quantity
										 */
										sl.addProductLedger(new ProductLedger(cursorPL.getString(2), cursorPL.getString(3), cursorPL.getDouble(4), cursorPL.getInt(6)));
										} while(cursorPL.moveToNext());
								}
							}
							cursorPL.close();
						saleList.add(sl);
					} while (cursorSL.moveToNext());
				}
			}
			cursorSL.close();
			db.close();
		return saleList;
		} catch (Exception e) {
			return saleList;
		}
	}
	
	/**
	 * Get Sale by using start and end date string
	 * @param from start date string
	 * @param to end date string
	 * @return list of found sales
	 */
	public ArrayList<SaleLedger> getSale(String from, String to) {
		ArrayList<SaleLedger> saleList = new ArrayList<SaleLedger>();
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			
			/** Get Sale Ledger **/
			String strSQL = String.format("SELECT * FROM %s WHERE Date BETWEEN '%s' AND '%s'", TABLE_SALE_LEDGER, from, to);
			Log.d("getSale", strSQL);
			Cursor cursorSL = db.rawQuery(strSQL, null);
			if (cursorSL != null) {
				if (cursorSL.moveToFirst()) {
					do {
						/**
						 * 0 = _key 1 = Date 2 = Total
						 */
						SaleLedger sl = new SaleLedger(cursorSL.getString(1), cursorSL.getDouble(2));
						String date = cursorSL.getString(1);
						
							/** Get SaleLineItem for that Sale Ledger **/
						strSQL = String.format("SELECT * FROM %s WHERE Date = '%s'", TABLE_PRODUCT_LEDGER, date);
						Cursor cursorPL = db.rawQuery(strSQL, null);
	
							if (cursorPL != null) {
								if (cursorPL.moveToFirst()) {
									do {
										/**
										 * 0 = _key 1 = Date 2 = ProductId 3 = ProductName 4 = UnitPrice
										 * 5 = Price 6 = Quantity
										 */
										sl.addProductLedger(new ProductLedger(cursorPL.getString(2), cursorPL.getString(3), cursorPL.getDouble(4), cursorPL.getInt(6)));
									} while (cursorPL.moveToNext());
								}
							}
							cursorPL.close();
						saleList.add(sl);
					} while (cursorSL.moveToNext());
				}
			}
			cursorSL.close();
			db.close();
			return saleList;
		} catch (Exception e) {
			return saleList;
		}
	}

	/**
	 * Remove sale in database by using date Calendar
	 * @param calendar date as Calendar
	 * @return row of deleted sale, -1 if failed
	 */
	public long removeSale(Calendar calendar) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH mm ss");
		String current = sdf.format(calendar);
		try {

			SQLiteDatabase db = this.getWritableDatabase();

			long rows = db.delete(TABLE_SALE_LEDGER, "Date=?",
					new String[] {current});
			db.delete(TABLE_PRODUCT_LEDGER, "Date=?",
					new String[] { current});

			db.close();
			return rows; // return rows delete.

		} catch (Exception e) {
			return -1;
		}
	}	
}
