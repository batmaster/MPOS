package com.bbaf.mpos.saleledger.ui;

import java.util.ArrayList;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.saleledger.ProductLedger;
import com.bbaf.mpos.saleledger.SaleLedger;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SaleLedgerProductListActivity extends Activity {
	
	private TextView textViewLedgerProductListDate;
	private TextView textViewLedgerProductListTotal;
	private ListView listViewLedgerProductList;
	private ProductLedgerListViewAdapter productLedgerListViewAdapter;
	private Button buttonLedgerProductBack;
	
	private SaleLedger sale;
	private ArrayList<ProductLedger> productList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale_ledger_product_list);
		
		sale = (SaleLedger) getIntent().getSerializableExtra("SaleLedger");
		
		productList = (ArrayList<ProductLedger>) getIntent().getSerializableExtra("ProductLedgerList");
		
		textViewLedgerProductListDate = (TextView)findViewById(R.id.textViewLedgerProductList);
		textViewLedgerProductListDate.setText(String.format("%s %s", sale.getDateX(), sale.getTimeX()));
		
		textViewLedgerProductListTotal = (TextView)findViewById(R.id.textViewLedgerProductListTotal);
		textViewLedgerProductListTotal.setText(String.format("Total : %.2f", sale.getTotal()));
		
		listViewLedgerProductList = (ListView)findViewById(R.id.listViewLedgerProductList);
		productLedgerListViewAdapter = new ProductLedgerListViewAdapter(this, productList);
		listViewLedgerProductList.setAdapter(productLedgerListViewAdapter);
		
		buttonLedgerProductBack = (Button)findViewById(R.id.buttonLedgerProductBack);
		buttonLedgerProductBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sale_ledger_list, menu);
		return true;
	}

}
