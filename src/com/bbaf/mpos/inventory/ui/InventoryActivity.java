package com.bbaf.mpos.inventory.ui;

import java.util.ArrayList;

import com.bbaf.mpos.ProductDescription;
import com.bbaf.mpos.ProductQuantity;
import com.bbaf.mpos.R;
import com.bbaf.mpos.R.id;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;
import com.bbaf.mpos.inventory.InventoryDBHelper;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.GridLayout.Spec;
import android.widget.TabHost.TabSpec;
import android.widget.TableLayout;
import android.widget.Toast;

public class InventoryActivity extends Activity {

	private ArrayList<ProductDescription> productList;

	private TabHost tabHost;
	private TabSpec tabInventory;
	private TableLayout tableLayout;
	private Button buttonEdit;
	private Button buttonRemove;
	private Button buttonRemoveAll;

	private TabSpec tabAdd;
	private EditText editTextProductId;
	private EditText editTextProductName;
	private EditText editTextPrice;
	private EditText editTextCost;
	private EditText editTextQuantity;
	private Button buttonScan;
	private Button buttonAdd;
	private Button buttonClear;

	private InventoryDBHelper dbHelper;
	// maybe collect as same location later
	private static final int EDIT_ACTIVITY_REQUESTCODE = 1;
	// not sure it is static value
	private static final int SCANNER_ACTIVITY_REQUESTCODE = 49374;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);

		dbHelper = new InventoryDBHelper(this);

		// initial tab host
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();

		tabInventory = tabHost.newTabSpec("tabInventory");
		tabInventory.setContent(R.id.tabInventory);
		tabInventory.setIndicator("Inventory");
		tabHost.addTab(tabInventory);

		tabAdd = tabHost.newTabSpec("tabAdd");
		tabAdd.setContent(R.id.tabAdd);
		tabAdd.setIndicator("Add");
		tabHost.addTab(tabAdd);
		//

		tableLayout = (TableLayout) findViewById(R.id.tableLayout);
		InventoryTableHead tableHead = new InventoryTableHead(this);
		tableLayout.addView(tableHead);
		buttonEdit = (Button) findViewById(R.id.buttonEdit);
		// next to line i was change to separate class if it crash u can comment it and use old one instead.
		OnClickListener editListener = new EditOnClickListener(tableLayout,dbHelper,this);
		buttonEdit.setOnClickListener(editListener);
/*		buttonEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// now, must edit every item that is checked
				for (int i = 1; i < tableLayout.getChildCount(); i++) {
					try {
						InventoryTableRow row = (InventoryTableRow) tableLayout
								.getChildAt(i);
						// can you break until first finish??
						if (row.isChecked()) {
							Intent editActivity = new Intent(
									getApplicationContext(),
									EditProductActivity.class);
							ProductDescription product = row.getProduct();
							editActivity
									.putExtra("ProductDescription", product);
							ProductQuantity quantity = dbHelper
									.getQuantity(product.getId());
							editActivity.putExtra("ProductQuantity", quantity);

							// EDIT_ACTIVITY_REQUESTCODE = 1
							startActivityForResult(editActivity,
									EDIT_ACTIVITY_REQUESTCODE);
						}

					} catch (ClassCastException e) {
						// prevent casting TableHead
					}
				}
			}
		});
*/
		buttonRemove = (Button) findViewById(R.id.buttonRemove);
		// next to line i was change to separate class if it crash u can comment it and use old one instead.
		OnClickListener removeListener = new RemoveOnClickListener(tableLayout,dbHelper,this);
		buttonRemove.setOnClickListener(removeListener);
/*		buttonRemove.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 1; i < tableLayout.getChildCount(); i++) {
					Log.d("rem", "loop");
					InventoryTableRow row = (InventoryTableRow) tableLayout
							.getChildAt(i);

					if (row.isChecked()) {
						dbHelper.removeProduct(row.getProduct());
					}
				}
				// tabHost.invalidate();
				// tabHost.refreshDrawableState();
				refreshTable();
			}
		});
*/
		buttonRemoveAll = (Button) findViewById(R.id.buttonRemoveAll);
		// next to line i was change to separate class if it crash u can comment it and use old one instead.
		OnClickListener removeAllListener = new RemoveAllOnClickListener(tableLayout,dbHelper,this);
		buttonRemoveAll.setOnClickListener(removeAllListener);
/*		buttonRemoveAll.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final AlertDialog.Builder adb = new AlertDialog.Builder(getApplicationContext());
				adb.setTitle("Confirm?");
				adb.setMessage("Plese Confirm");
				adb.setNegativeButton("Cancel", null);
				adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						for (int i = 1; i < tableLayout.getChildCount(); i++) {
							try {
								InventoryTableRow row = (InventoryTableRow) tableLayout
										.getChildAt(i);

								dbHelper.removeProduct(row.getProduct());
							} catch (ClassCastException e) {
								// prevent casting TableHead
							}
						}
						// tabHost.invalidate();
						// tabHost.refreshDrawableState();
						refreshTable();
						
					}
				});
				adb.show();
			}
		});
*/
		buttonRemoveAll.setVisibility(View.GONE);

		editTextProductId = (EditText) findViewById(R.id.editTextProductId);
		editTextProductName = (EditText) findViewById(R.id.editTextProductName);
		editTextPrice = (EditText) findViewById(R.id.editTextPrice);
		editTextCost = (EditText) findViewById(R.id.editTextCost);
		editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);
		buttonScan = (Button) findViewById(R.id.buttonScan);
		// next to line i was change to separate class if it crash u can comment it and use old one instead.
		OnClickListener scanListener = new ScanOnClickListener(this);
		buttonScan.setOnClickListener(scanListener);
/*		buttonScan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(
						InventoryActivity.this);
				scanIntegrator.initiateScan();
			}
		});
*/
		buttonAdd = (Button) findViewById(R.id.buttonAdd);
		// next to line i was change to separate class if it crash u can comment it and use old one instead.
		OnClickListener addListener = new AddOnClickListener(dbHelper,this);
		buttonAdd.setOnClickListener(addListener);
/*		buttonAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String id = editTextProductId.getText().toString();
				if (!id.equals("")) {
					Log.d("table", dbHelper.getProduct(id) + "");
					if (dbHelper.getProduct(id) != null) {
						Toast.makeText(
								getApplicationContext(),
								String.format("Product : %s is already added.",
										id), Toast.LENGTH_SHORT).show();
					} else {
						String name = editTextProductName.getText().toString();
						String priceText = editTextPrice.getText().toString();
						String costText = editTextCost.getText().toString();
						String quantityText = editTextQuantity.getText()
								.toString();

						double price = Double.parseDouble(priceText.equals("") ? "0"
								: priceText);
						double cost = Double.parseDouble(costText.equals("") ? "0"
								: costText);
						int quantity = Integer.parseInt(quantityText.equals("") ? "1"
								: quantityText);
						ProductDescription product = new ProductDescription(id,
								name, price, cost);

						long row = dbHelper.addProduct(product);
						dbHelper.setQuantity(product, quantity);

						Toast.makeText(
								getApplicationContext(),
								String.format(
										"Product add to row %d successfully.",
										row), Toast.LENGTH_SHORT).show();
						clear();
						// tabHost.invalidate();
						// tabHost.refreshDrawableState();
						refreshTable();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Product ID must not be empty.", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});
*/
		buttonClear = (Button) findViewById(R.id.buttonClear);
		// next to line i was change to separate class if it crash u can comment it and use old one instead.
		// also with clear() method i comment it and move it to ClearOnClickListener
		OnClickListener clearListener = new ClearOnClickListener(this);
		buttonClear.setOnClickListener(clearListener);
/*		buttonClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				clear();
			}
		});
*/
		// tabHost.invalidate();
		// tabHost.refreshDrawableState();
		refreshTable();
	}

/*	public void clear() {
		editTextProductId.setText("");
		editTextProductName.setText("");
		editTextPrice.setText("");
		editTextCost.setText("");
		editTextQuantity.setText("");
	}
*/
	//change to public because i can't use it when use in different class.
	public void refreshTable() {
		tableLayout.removeAllViews();
		tableLayout.addView(new InventoryTableHead(this));

		productList = dbHelper.getAllProduct();
		Toast.makeText(getApplicationContext(), productList.toString(),
				Toast.LENGTH_SHORT);
		if (productList != null) {
			if (productList.size() == 0) {
				TableRow free = new TableRow(this);
				TextView c = new TextView(this);
				free.addView(c);
				TextView v = new TextView(this);
				v.setText("Empty");
				free.addView(v);
				tableLayout.addView(free);
				return;
			}
			for (int i = 0; i < productList.size(); i++) {
				ProductDescription product = productList.get(i);
				String id = product.getId();
				ProductQuantity quantity = dbHelper.getQuantity(id);

				InventoryTableRow row = new InventoryTableRow(this,
						productList.get(i), quantity);
				tableLayout.addView(row);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inventory, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// for tracking
		Log.d("res", "requestCode " + requestCode);
		Log.d("res", "resultCode " + resultCode);

		if (requestCode == EDIT_ACTIVITY_REQUESTCODE) {
			/**
			 * 0 = EDIT_CANCEL 1 = EDIT_SUCCESS
			 */
			if (resultCode == 0) {
				// no need to refresh
			} else if (resultCode == 1) {
				// tabHost.invalidate();
				// tabHost.refreshDrawableState();
				refreshTable();
			}
		} else if (requestCode == SCANNER_ACTIVITY_REQUESTCODE) {
			IntentResult scanningResult = IntentIntegrator.parseActivityResult(
					requestCode, resultCode, data);

			// although scanner is canceled, scanningResult is not null but
			// scanningResult.getContents()
			if (scanningResult != null) {
				editTextProductId.setText(scanningResult.getContents());
				editTextProductName.requestFocus();
			} else {
				Toast.makeText(getApplicationContext(),
						"No scan data received!", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public EditText[] getAllEditText() {
		return new EditText[]{editTextProductId,editTextProductName,editTextPrice,editTextCost,editTextQuantity};
	}
}
