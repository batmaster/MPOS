package com.bbaf.mpos.sale.ui;

import java.util.ArrayList;

import com.bbaf.mpos.ProductDescription;
import com.bbaf.mpos.R;
import com.bbaf.mpos.R.array;
import com.bbaf.mpos.R.id;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;
import com.bbaf.mpos.inventory.InventoryDBHelper;
import com.bbaf.mpos.inventory.ui.ClearOnClickListener;
import com.bbaf.mpos.inventory.ui.InventoryActivity;
import com.bbaf.mpos.inventory.ui.InventoryTableHead;
import com.bbaf.mpos.inventory.ui.InventoryTableRow;
import com.bbaf.mpos.sale.Register;
import com.bbaf.mpos.sale.SaleLineItem;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class SaleActivity extends Activity {
	/////////////////////
	private String[] titles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    /////////////////////

	private TextView textViewStatus;
	private TextView textViewTotalSalePrice;
	
	private TableLayout tableLayoutSale;
	
	private EditText editTextId;
	private EditText editTextQuantity;
	private Button buttonAddSale;
	
	private Button buttonSubmitSale;
	private Button buttonClearSale;
	private Button buttonInventory;
	
	private Register register;
	private InventoryDBHelper dbDAO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale);
		
		register = (Register) getIntent().getSerializableExtra("register");
		
		dbDAO = InventoryDBHelper.getInstance(this);
		
		textViewStatus = (TextView)findViewById(R.id.textViewStatus);
		textViewStatus.setText("Welcome");
		
		textViewTotalSalePrice = (TextView)findViewById(R.id.textViewTotalSalePrice);
		textViewTotalSalePrice.setText("0.0");
		
		tableLayoutSale = (TableLayout)findViewById(R.id.tableLayoutSale);
		SaleTableHead tableHead = new SaleTableHead(this);
		tableLayoutSale.addView(tableHead);
		tableLayoutSale.setShrinkAllColumns(false);
		tableLayoutSale.setStretchAllColumns(true);
		
		editTextId = (EditText)findViewById(R.id.editTextId);
		
		editTextQuantity = (EditText)findViewById(R.id.editTextQuantity);
		
		buttonAddSale = (Button)findViewById(R.id.buttonAddSale);
		buttonAddSale.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String id = editTextId.getText().toString();
				if (id.equals("")) {
					Toast.makeText(getApplicationContext(), "ID must not be empty.", Toast.LENGTH_SHORT).show();
				}
				else {
					ProductDescription product = dbDAO.getProduct(id);
					if (product == null) {
						Toast.makeText(getApplicationContext(), "ID has not registered.", Toast.LENGTH_SHORT).show();
					}
					else {
						String quantityText = editTextQuantity.getText().toString();
						int quantity = quantityText.equals("") ? 1 : Integer.parseInt(quantityText);
						int tmp = 0;
						if (register.getSaleLineItemList(product) != null)
							tmp = register.getSaleLineItemList(product).getQuantity();
						int stock = dbDAO.getQuantity(id) - tmp;
						
						if (stock < quantity) {
							Toast.makeText(getApplicationContext(), "ProductId: " + product.getId() + " has only " + stock + ".", Toast.LENGTH_SHORT).show();
						}
						else {
							if (register.addItem(product, quantity)) {
								// not sure it should be here
								Toast.makeText(getApplicationContext(), "ProductId: " + product.getId() + " is added successfully.", Toast.LENGTH_SHORT).show();
								refreshTable();
								
								String status = product.getName() + " : " + quantity + " = " + quantity*product.getPrice() + " Bht.";
								textViewStatus.setText(status);
								
							}
							else {
								Toast.makeText(getApplicationContext(), "Adding not successful.", Toast.LENGTH_SHORT).show();
							}
						}
					}
				}
			}
		});

		buttonSubmitSale = (Button)findViewById(R.id.buttonSubmitSale);
		buttonSubmitSale.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		buttonClearSale = (Button)findViewById(R.id.buttonClearSale);
		buttonClearSale.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				register.removeAllItem();
				refreshTable();
				textViewStatus.setText("Welcome");
				textViewTotalSalePrice.setText("0.0");
				editTextId.setText("");
				editTextQuantity.setText("");
			}
		});
		
		buttonInventory = (Button)findViewById(R.id.buttonInventory);
		buttonInventory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent inventoryActivity = new Intent(getApplicationContext(), InventoryActivity.class);
       			startActivity(inventoryActivity);
       			finish();
				
			}
		});
		
		
		///////////////////// still not work yet
		titles = getResources().getStringArray(R.array.activities_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, titles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.d("ges", position + " " + id);
				
		           if (position == 1) {
		        	   	Intent inventoryActivity = new Intent(getApplicationContext(), InventoryActivity.class);
		       			startActivity(inventoryActivity);
		           }
				
			}
        	
        });
        /////////////////////
		
	}
	
	public void refreshTable() {
		tableLayoutSale.removeAllViews();
		tableLayoutSale.addView(new SaleTableHead(this));

		ArrayList<SaleLineItem> saleLineItem = register.getAllSaleLineItemList();

		if (saleLineItem != null) {
			if (saleLineItem.size() == 0) {
				TableRow free = new TableRow(this);
				TextView c = new TextView(this);
				free.addView(c);
				TextView v = new TextView(this);
				v.setText("Empty");
				free.addView(v);
				tableLayoutSale.addView(free);
				return;
			}
			for (int i = 0; i < saleLineItem.size(); i++) {
				ProductDescription product = saleLineItem.get(i).getProductDescription();
				double unitPrice = product.getPrice();
				int quantity = saleLineItem.get(i).getQuantity();
				String id = product.getId();

				SaleTableRow row = new SaleTableRow(this,
						product, unitPrice, quantity);
				tableLayoutSale.addView(row);
			}
			
			textViewTotalSalePrice.setText(String.valueOf(register.getTotal()));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sale, menu);
		return true;
	}

}
