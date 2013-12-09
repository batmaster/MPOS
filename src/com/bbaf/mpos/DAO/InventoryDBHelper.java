package com.bbaf.mpos.DAO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bbaf.mpos.ProductDescription.ProductDescription;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Inventory DAO, for contact between java object and database.
 */
public class InventoryDBHelper extends SQLiteOpenHelper implements Serializable {

	private static final String DATABASE_NAME = "bbafmpos.db";
	private static final String TABLE_INVENTORY = "inventory";
	private static final String TABLE_QUANTITY = "quantity";
	private static final int DATABASE_VERSION = 1;
	private static InventoryDBHelper dao;

	private InventoryDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * Get InventoryDBHelper object by initializing context.
	 * Only for first using InventoryDBHelper.
	 * @param context context of calling activity
	 * @return InventoryDBHelper object
	 */
	public static InventoryDBHelper getInstance(Context context) {
		if(dao==null) dao = new InventoryDBHelper(context);
		return dao;
	}
	
	/**
	 * Get InventoryDBHelper object.
	 * @return InventoryDBHelper object
	 */
	public static InventoryDBHelper getInstance() {
		return dao;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// date text yyyy-MM-dd HH:mm:ss
		String sql = String
				.format("CREATE TABLE %s (_key INTEGER PRIMARY KEY, ProductId TEXT, ProductName TEXT, Price DOUBLE, Cost DOUBLE, DateModified TEXT);",
						TABLE_INVENTORY);
		db.execSQL(sql);

		sql = String
				.format("CREATE TABLE %s (ProductId INTEGER, ProductQuantity INTEGER);",
						TABLE_QUANTITY);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "USE TABLE IF EXISTS " + TABLE_INVENTORY;
		db.execSQL(sql);

		sql = "USE TABLE IF EXISTS " + TABLE_QUANTITY;
		db.execSQL(sql);
	}

	/**
	 * Add ProductDescription to database.
	 * @param product ProductDescription
	 * @param quantity quantity of product
	 * @return row of added ProductDescription to database, -1 if failed
	 */
	public long addProduct(ProductDescription product,int quantity) {
		try {
			long rows = 0;
			
			
			if(getProduct(product.getId())==null) {
				ContentValues value = new ContentValues();
				value.put("ProductId", product.getId());
				value.put("ProductName", product.getName());
				value.put("Price", product.getPrice());
				value.put("Cost", product.getCost());
			
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String current = sdf.format(new Date());
				value.put("DateModified", current);
				
				SQLiteDatabase db = this.getWritableDatabase();
				
				rows = db.insert(TABLE_INVENTORY, null, value);
				addQuantity(product,quantity);

				db.close();
			}
			else {
				rows = addQuantity(product, quantity);
			}
			return rows; // return rows inserted.

		} catch (Exception e) {
			Log.d("add product", e.toString());
			return -1;
		}
	}
	
	/**
	 * Add quantity of ProductDescription to database.
	 * @param product ProductDescription
	 * @param quantity quantity of product
	 * @return row of added quantity to database, -1 if failed
	 */
	public long addQuantity(ProductDescription product, int quantity) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues value = new ContentValues();
			value.put("ProductId", product.getId());
			value.put("ProductQuantity", quantity);

			long rows = db.insert(TABLE_QUANTITY, null, value);

			db.close();
			return rows; // return rows inserted.

		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * Set quantity of ProductDescription in database.
	 * @param product ProductDescription
	 * @param quantity new quantity of product
	 * @return row of added quantity to database, -1 if failed
	 */
	public long setQuantity(ProductDescription product, int quantity) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues value = new ContentValues();
			value.put("ProductId", product.getId());
			value.put("ProductQuantity", quantity);

			//long rows = db.insert(TABLE_QUANTITY, null, value);
			long rows = db.update(TABLE_QUANTITY, value, " ProductId=?",
					new String[] { String.valueOf(product.getId()) });

			db.close();
			return rows; // return rows inserted.

		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * Return ProductDescription using id text as key.
	 * @param id id text of product
	 * @return ProductDescription object
	 */
	public ProductDescription getProduct(String id) {
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			ProductDescription product = null;
			Cursor cursor = db.query(TABLE_INVENTORY, new String[] { "*" },
					"ProductId=?", new String[] { id }, null,
					null, null, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					/**
					 * 0 = _key 1 = ProductId 2 = ProductName 3 = Price 4 = Cost
					 * 5 = DateModified
					 */
					product = new ProductDescription(cursor.getInt(0),
							cursor.getString(1), cursor.getString(2),
							cursor.getDouble(3), cursor.getDouble(4),
							cursor.getString(5));
				}
			}
			cursor.close();
			db.close();
			return product;
		} catch (Exception e) {
			Log.d("table", "ex");
			return null;
		}
	}

	/**
	 * Get all ProductDescription in database.
	 * @return list of all product in database
	 */
	public ArrayList<ProductDescription> getAllProduct() {
		try {
			ArrayList<ProductDescription> productList = new ArrayList<ProductDescription>();

			SQLiteDatabase db = this.getReadableDatabase();

			String strSQL = "SELECT  * FROM " + TABLE_INVENTORY;
			Cursor cursor = db.rawQuery(strSQL, null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						ProductDescription product = new ProductDescription(
								cursor.getInt(0), cursor.getString(1),
								cursor.getString(2), cursor.getDouble(3),
								cursor.getDouble(4), cursor.getString(5));
						productList.add(product);
					} while (cursor.moveToNext());
				}
			}
			cursor.close();
			db.close();
			return productList;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Get quantity of product by using id text as key.
	 * @param id id text of product
	 * @return quantity of product, -1 if failed
	 */
	public int getQuantity(String id) {
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			int quantity = -1;
			Cursor cursor = db.query(TABLE_QUANTITY, new String[] { "*" },
					"ProductId=?", new String[] { id }, null,
					null, null, null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					quantity = cursor.getInt(1);
				}
			}
			cursor.close();
			db.close();
			return quantity;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * Edit ProductDescription in database.
	 * @param oldProduct old product description
	 * @param newProduct new product description
	 * @return row of updated product description, -1 if failed
	 */
	public long editProduct(ProductDescription oldProduct,
			ProductDescription newProduct) {
		try {

			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues value = new ContentValues();
			value.put("ProductId", newProduct.getId());
			value.put("ProductName", newProduct.getName());
			value.put("Price", newProduct.getPrice());
			value.put("Cost", newProduct.getCost());
			long rows = db.update(TABLE_INVENTORY, value, " ProductId=?",
					new String[] { String.valueOf(oldProduct.getId()) });

			db.close();
			return rows;

		} catch (Exception e) {
			return -1;
		}

	}

	/**
	 * Edit quantity by using old and new product description.
	 * Used when edit both id and quantity at the same time
	 * @param oldProduct old product description
	 * @param newProduct new product description
	 * @param newQuantity new quantity of product
	 * @return row of updated product quantity, -1 if failed
	 */
	public long editQuantity(ProductDescription oldProduct, ProductDescription newProduct,
			int newQuantity) {
		try {

			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues value = new ContentValues();
			value.put("ProductId", newProduct.getId());
			value.put("ProductQuantity", newQuantity);
			long rows = db.update(TABLE_QUANTITY, value, " ProductId=?",
					new String[] { String.valueOf(oldProduct.getId()) });

			db.close();
			return rows;

		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * Remove ProductDescription in database.
	 * @param product ProductDescription
	 * @return row of deleted product in database, -1 if failed
	 */
	public long removeProduct(ProductDescription product) {
		try {

			SQLiteDatabase db = this.getWritableDatabase();

			long rows = db.delete(TABLE_INVENTORY, "ProductId=?",
					new String[] { String.valueOf(product.getId()) });
			db.delete(TABLE_QUANTITY, "ProductId=?",
					new String[] { String.valueOf(product.getId()) });

			db.close();
			return rows; // return rows delete.

		} catch (Exception e) {
			return -1;
		}
	}
	
	/**
	 * Get ProductDescription by using a part of product id or name.
	 * @param something some part of product id or name
	 * @return list of found products
	 */
	public ArrayList<ProductDescription> getProductBySomething(String something) {
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			ArrayList<ProductDescription> productList = new ArrayList<ProductDescription>();
			
			Cursor cursor = db.query(TABLE_INVENTORY, new String[] { "*" }, 
	                "ProductName LIKE '%" + something + "%'", null, null, null, null);
			
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						ProductDescription product = new ProductDescription(
								cursor.getInt(0), cursor.getString(1),
								cursor.getString(2), cursor.getDouble(3),
								cursor.getDouble(4), cursor.getString(5));
						productList.add(product);
					} while (cursor.moveToNext());
				}
			}
			cursor.close();
			db.close();
			return productList;
		} catch (Exception e) {
			Log.d("table", "ex");
			return null;
		}
	}
}
