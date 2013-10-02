package com.bbaf.mpos.inventory.ui;

import java.util.ArrayList;

import com.bbaf.mpos.ProductDescription;
import com.bbaf.mpos.ProductQuantity;
import com.bbaf.mpos.R;
import com.bbaf.mpos.R.id;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;
import com.bbaf.mpos.inventory.InventoryDBHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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

	private TabSpec tabAdd;
	private EditText editTextProductId;
	private EditText editTextProductName;
	private EditText editTextPrice;
	private EditText editTextCost;
	private Button buttonAdd;
	private Button buttonClear;

	private InventoryDBHelper dbHelper;

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
		buttonEdit = (Button)findViewById(R.id.buttonEdit);
		buttonEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		buttonRemove = (Button)findViewById(R.id.buttonRemove);
		buttonRemove.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for (int i = 1; i < tableLayout.getChildCount(); i++) {
					Log.d("rem", "loop");
					InventoryTableRow row = (InventoryTableRow)tableLayout.getChildAt(i);
					
					if (row.isChecked()) {
						dbHelper.removeProduct(row.getProduct());
					}
				}
				Log.d("rem", "be refresh");
				refreshTable();
				Log.d("rem", "af refresh");
			}
		});

		editTextProductId = (EditText) findViewById(R.id.editTextProductId);
		editTextProductName = (EditText) findViewById(R.id.editTextProductName);
		editTextPrice = (EditText) findViewById(R.id.editTextPrice);
		editTextCost = (EditText) findViewById(R.id.editTextCost);
		buttonAdd = (Button) findViewById(R.id.buttonAdd);
		buttonAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String idText = editTextProductId.getText().toString();
				if (!idText.equals("")) {
					int id = Integer.parseInt(idText);
					Log.d("table", dbHelper.getProduct(id) + "");
					if (dbHelper.getProduct(id) != null) {
						Toast.makeText(getApplicationContext(), String.format(
								"Product : %s is already added.", id),
								Toast.LENGTH_SHORT).show();
					} else {
						String name = editTextProductName.getText().toString();
						String priceText = editTextPrice.getText().toString();
						String costText = editTextCost.getText().toString();
						
						double price = Double.parseDouble(priceText.equals("") ? "0" : priceText);
						double cost = Double.parseDouble(costText.equals("") ? "0" : costText);
						ProductDescription product = new ProductDescription(id, name, price, cost);
						long row = dbHelper.addProduct(product);
						dbHelper.setQuantity(product, 0);

						Toast.makeText(getApplicationContext(), String.format(
								"Product add to row %d successfully.", row),
								Toast.LENGTH_SHORT).show();
						refreshTable();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Product ID must not be empty.", Toast.LENGTH_SHORT).show();
				}

			}
		});

		buttonClear = (Button) findViewById(R.id.buttonClear);
		buttonClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				editTextProductId.setText("");
				editTextProductName.setText("");
				editTextPrice.setText("");
				editTextCost.setText("");
			}
		});

		refreshTable();
	}

	private void refreshTable() {
		tableLayout.removeAllViews();
		tableLayout.addView(new InventoryTableHead(this));
		
		productList = dbHelper.getAllProduct();
		Toast.makeText(getApplicationContext(), productList.toString(), Toast.LENGTH_SHORT);
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
				int id = product.getId();
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

}
