package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.R;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TabHost.TabSpec;

public class InventoryActivity2 extends Activity {

	private TabHost tabHost;
	private TabSpec tabInventory;
	private TableLayout tableLayout;
//	private Button buttonEdit;
//	private Button buttonRemove;
//	private Button buttonRemoveAll;
//	private EditText editTextProductId;
//	private EditText editTextProductName;
//	private EditText editTextPrice;
//	private EditText editTextCost;
//	private EditText editTextQuantity;
//	private Button buttonScan;
//	private Button buttonAdd;
//	private Button buttonClear;
	
	
	private TabSpec tabSale;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory_activity2);
		
		tabHost = (TabHost) findViewById(R.id.tabhost2);
		tabHost.setup();

		tabSale = tabHost.newTabSpec("tabSale");
		tabSale.setContent(R.id.tabSale);
		tabSale.setIndicator("Sale");
		tabHost.addTab(tabSale);
		
		tabInventory = tabHost.newTabSpec("tabInventory");
		tabInventory.setContent(R.id.tabInventory2);
		tabInventory.setIndicator("Inventory");
		tabHost.addTab(tabInventory);
		
		//TODO adddddd!!!!!!
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inventory_activity2, menu);
		return true;
	}

}
