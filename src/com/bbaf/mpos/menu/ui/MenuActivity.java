package com.bbaf.mpos.menu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bbaf.mpos.R;
import com.bbaf.mpos.DAO.InventoryDBHelper;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.inventoryAndSale.ui.InventoryandSaleActivity;
import com.bbaf.mpos.saleledger.Ledger;
import com.bbaf.mpos.saleledger.ui.LedgerActivity;

public class MenuActivity extends Activity {
	
	private Button buttonToInvenSale; 
	private Button buttonToLedger; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		Register.getInstance().getInventory().setDB((InventoryDBHelper.getInstance(getApplicationContext())));
		
		buttonToInvenSale = (Button)findViewById(R.id.buttonToInvenSale);
		buttonToLedger = (Button)findViewById(R.id.buttonToLedger);
		
		//// bat try to do this, otherwise cannot submit sale
		Register.getInstance().setLedger(new Ledger(this));
		
		////
		
		buttonToInvenSale.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent toInvenSale = new Intent(getApplicationContext(),
						InventoryandSaleActivity.class);
				startActivity(toInvenSale);
			}
		});
		
		buttonToLedger.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent toLedger = new Intent(getApplicationContext(),
						LedgerActivity.class);
				startActivity(toLedger);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
