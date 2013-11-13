package com.bbaf.mpos.inventory.ui;

import java.util.ArrayList;

import com.bbaf.mpos.ProductDescription;
import com.bbaf.mpos.ProductQuantity;
import com.bbaf.mpos.R;
import com.bbaf.mpos.inventory.InventoryDBHelper;
import com.bbaf.mpos.sale.Inventory;
import com.bbaf.mpos.sale.Register;
import com.bbaf.mpos.sale.ui.SaleActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.app.Activity;
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
import android.widget.TabHost.TabSpec;
import android.widget.TableLayout;
import android.widget.Toast;

public class InventoryActivity extends Activity {

	@Override
	protected void onResume() {
		super.onResume();
		refreshTable();
	}

	private ArrayList<ProductDescription> productList;

	private TabHost tabHost;
	private TabSpec tabInventory;
	private TableLayout tableLayout;
	private Button buttonEdit;
	private Button buttonRemove;
	private Button buttonRemoveAll;
	////
	private Button buttonSale;
	private EditText editTextSomething;
	private Button buttonFilter;
	////

	private TabSpec tabAdd;
	private EditText editTextProductId;
	private EditText editTextProductName;
	private EditText editTextPrice;
	private EditText editTextCost;
	private EditText editTextQuantity;
	private Button buttonScan;
	private Button buttonAdd;
	private Button buttonClear;
	
	private Register register;

	private InventoryDBHelper dbDAO;
	// bat: maybe collect at same location later
	private static final int EDIT_ACTIVITY_REQUESTCODE = 1;
	private static final int SCANNER_ACTIVITY_REQUESTCODE = 49374;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);
		dbDAO = InventoryDBHelper.getInstance(this);
		register = Register.getInstance(new Inventory());
		// bat: initial tab host
		
		//TODO test
		
		Intent inven2 = new Intent(
				getApplicationContext(),
				InventoryActivity2.class);
		
		this.startActivity(inven2);
		
		
//		tabHost = (TabHost) findViewById(R.id.tabhost);
//		tabHost.setup();
//
//		tabInventory = tabHost.newTabSpec("tabInventory");
//		tabInventory.setContent(R.id.tabInventory);
//		tabInventory.setIndicator("Inventory");
//		tabHost.addTab(tabInventory);
//
//		tabAdd = tabHost.newTabSpec("tabAdd");
//		tabAdd.setContent(R.id.tabAdd);
//		tabAdd.setIndicator("Add");
//		tabHost.addTab(tabAdd);
		//

		tableLayout = (TableLayout) findViewById(R.id.tableLayout);
		InventoryTableHead tableHead = new InventoryTableHead(this);
		tableLayout.addView(tableHead);
		tableLayout.setShrinkAllColumns(false);
		tableLayout.setStretchAllColumns(true);

		buttonEdit = (Button) findViewById(R.id.buttonEdit);
		OnClickListener editListener = new EditOnClickListener(tableLayout,
				dbDAO, this);
		buttonEdit.setOnClickListener(editListener);

		buttonRemove = (Button) findViewById(R.id.buttonRemove);
		OnClickListener removeListener = new RemoveOnClickListener(tableLayout,
				dbDAO, this);
		buttonRemove.setOnClickListener(removeListener);

		buttonRemoveAll = (Button) findViewById(R.id.buttonRemoveAll);
		OnClickListener removeAllListener = new RemoveAllOnClickListener(
				tableLayout, dbDAO, this);
		buttonRemoveAll.setOnClickListener(removeAllListener);
		//buttonRemoveAll.setVisibility(View.GONE);
		
		////
		buttonSale = (Button)findViewById(R.id.buttonSale);
		buttonSale.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!register.isSale()) register.startSale(); 
				Intent testSale = new Intent(getApplicationContext(),SaleActivity.class);
				//testSale.putExtra("register", register);
				startActivity(testSale);
				//finish();
				
			}
		});
		
		editTextSomething = (EditText)findViewById(R.id.editTextSomething);
		
		buttonFilter = (Button)findViewById(R.id.buttonFilter);
		buttonFilter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (!editTextSomething.getText().toString().equals("")) {
					ArrayList<ProductDescription> filtered = register.getInventory().getProductBySomething(editTextSomething.getText().toString());
					refreshTableFilter(filtered);
				}
				else {
					refreshTable();
				}
				
			}
		});
		////

		editTextProductId = (EditText) findViewById(R.id.editTextProductId);
		editTextProductName = (EditText) findViewById(R.id.editTextProductName);
		editTextPrice = (EditText) findViewById(R.id.editTextPrice);
		editTextCost = (EditText) findViewById(R.id.editTextCost);
		editTextQuantity = (EditText) findViewById(R.id.editTextQuantity2);

		buttonScan = (Button) findViewById(R.id.buttonScan);
		OnClickListener scanListener = new ScanOnClickListener(this);
		buttonScan.setOnClickListener(scanListener);

		buttonAdd = (Button) findViewById(R.id.buttonAddSale2);
		OnClickListener addListener = new AddOnClickListener(dbDAO, this);
		buttonAdd.setOnClickListener(addListener);

		buttonClear = (Button) findViewById(R.id.buttonClear);
		OnClickListener clearListener = new ClearOnClickListener(this);
		buttonClear.setOnClickListener(clearListener);

		refreshTable();
	}

	// arm: change to public because i can't use it when use in different class.
	public void refreshTable() {
		tableLayout.removeAllViews();
		tableLayout.addView(new InventoryTableHead(this));

		productList = dbDAO.getAllProduct();
		
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
				int quantity = dbDAO.getQuantity(id);

				InventoryTableRow row = new InventoryTableRow(this,
						productList.get(i), quantity);
				tableLayout.addView(row);
			}
		}
	}
	
	public void refreshTableFilter(ArrayList<ProductDescription> productList) {
		tableLayout.removeAllViews();
		tableLayout.addView(new InventoryTableHead(this));
		
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
				int quantity = dbDAO.getQuantity(id);

				InventoryTableRow row = new InventoryTableRow(this,
						productList.get(i), quantity);
				tableLayout.addView(row);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.inventory, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// bat: for tracking whether from which Activity and what is the result
		Log.d("result", "requestCode " + requestCode);
		Log.d("result", "resultCode " + resultCode);
		//
		if (requestCode == EDIT_ACTIVITY_REQUESTCODE) {
			/**
			 * 0 = EDIT_CANCEL 1 = EDIT_SUCCESS
			 */
			if (resultCode == 0) {
				// no need to refresh
			} else if (resultCode == 1) {
				refreshTable();
			}
		} else if (requestCode == SCANNER_ACTIVITY_REQUESTCODE) {
			IntentResult scanningResult = IntentIntegrator.parseActivityResult(
					requestCode, resultCode, data);
			// bat: although scanner is canceled, scanningResult is not null
			// but scanningResult.getContents()
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
		return new EditText[] { editTextProductId, editTextProductName,
				editTextPrice, editTextCost, editTextQuantity };
	}
}
