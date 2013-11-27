package com.bbaf.mpos.saleledger.ui;

import java.util.ArrayList;
import java.util.Date;

import com.bbaf.mpos.R;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.sale.Sale;
import com.bbaf.mpos.sale.ui.SaleTableRow;
import com.bbaf.mpos.saleledger.Ledger;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.TableLayout;

public class LedgerUIActivity extends Activity {
	
	private TabHost tabLedger;
	private TabSpec tabByDate;
	private TableLayout tableLayoutDate;
	private TabSpec tabByWeek;
	private TableLayout tableLayoutWeek;
	private TabSpec tabByMonth;
	private TableLayout tableLayoutMonth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ledger_ui);
		
		tabLedger = (TabHost) findViewById(R.id.tabLedger);
		tabLedger.setup();
		
		tabByDate = tabLedger.newTabSpec("tabdate");
		tabByDate.setContent(R.id.tabdate);
		tabByDate.setIndicator("By Date");
		tabLedger.addTab(tabByDate);
		tableLayoutDate = (TableLayout)findViewById(R.id.tableLayoutDate);
		

		tabByWeek = tabLedger.newTabSpec("tabweek");
		tabByWeek.setContent(R.id.tabweek);
		tabByWeek.setIndicator("By Week");
		tabLedger.addTab(tabByWeek);
		tableLayoutWeek = (TableLayout)findViewById(R.id.tableLayoutWeek);
		
		tabByMonth = tabLedger.newTabSpec("tabmonth");
		tabByMonth.setContent(R.id.tabmonth);
		tabByMonth.setIndicator("By Month");
		tabLedger.addTab(tabByMonth);
		tableLayoutMonth = (TableLayout)findViewById(R.id.tableLayoutMonth);
		
		datePlease();
		weekPlease();
		monthPlease();
	}
	
	public void datePlease() {
		tableLayoutDate.removeAllViews();
		Register r = Register.getInstance();
		Ledger l = r.getLedger();
		ArrayList<Sale> sales = l.getDaily();
		Log.d("led", sales.toString());
		if (sales != null) {
			if (sales.size() == 0) {
				TableRow free = new TableRow(this);
				TextView c = new TextView(this);
				free.addView(c);
				TextView v = new TextView(this);
				v.setText("Empty");
				free.addView(v);
				tableLayoutDate.addView(free);
				return;
			}
			for (int i = 0; i < sales.size(); i++) {
				Date date = sales.get(i).getDate();
				double total = sales.get(i).getTotal();
				
				LedgerTableRow row = new LedgerTableRow(this, sales.get(i));
				tableLayoutDate.addView(row);
			}
			
			//textViewTotalPriceText.setText(String.valueOf(Register.getInstance().getTotal()));
		}	
	}
	
	public void weekPlease() {
		tableLayoutWeek.removeAllViews();
		Register r = Register.getInstance();
		Ledger l = r.getLedger();
		ArrayList<Sale> sales = l.getWeek();
		Log.d("led", sales.toString());
		if (sales != null) {
			if (sales.size() == 0) {
				TableRow free = new TableRow(this);
				TextView c = new TextView(this);
				free.addView(c);
				TextView v = new TextView(this);
				v.setText("Empty");
				free.addView(v);
				tableLayoutWeek.addView(free);
				return;
			}
			for (int i = 0; i < sales.size(); i++) {
				Date date = sales.get(i).getDate();
				double total = sales.get(i).getTotal();
				
				LedgerTableRow row = new LedgerTableRow(this, sales.get(i));
				tableLayoutWeek.addView(row);
			}
			
			//textViewTotalPriceText.setText(String.valueOf(Register.getInstance().getTotal()));
		}	
	}
	
	public void monthPlease() {
		tableLayoutMonth.removeAllViews();
		Register r = Register.getInstance();
		Ledger l = r.getLedger();
		ArrayList<Sale> sales = l.getMonth();
		Log.d("led", sales.toString());
		if (sales != null) {
			if (sales.size() == 0) {
				TableRow free = new TableRow(this);
				TextView c = new TextView(this);
				free.addView(c);
				TextView v = new TextView(this);
				v.setText("Empty");
				free.addView(v);
				tableLayoutMonth.addView(free);
				return;
			}
			for (int i = 0; i < sales.size(); i++) {
				Date date = sales.get(i).getDate();
				double total = sales.get(i).getTotal();
				
				LedgerTableRow row = new LedgerTableRow(this, sales.get(i));
				tableLayoutMonth.addView(row);
			}
			
			//textViewTotalPriceText.setText(String.valueOf(Register.getInstance().getTotal()));
		}	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ledger_ui, menu);
		return true;
	}

}
