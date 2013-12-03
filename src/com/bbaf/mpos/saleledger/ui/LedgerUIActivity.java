package com.bbaf.mpos.saleledger.ui;

import java.util.ArrayList;
import java.util.Date;

import com.bbaf.mpos.R;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.sale.Sale;
import com.bbaf.mpos.saleledger.Ledger;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.TableLayout;

public class LedgerUIActivity extends Activity {
	
	private TabHost tabLedger;
	private TabSpec tabByDate;
	private ListView listViewSaleLedgerDate;
	private SaleLedgerListViewAdapter saleLedgerListViewAdapterDate;
	private TextView textViewTotalByDate;
	
	private TabSpec tabByWeek;
	private ListView listViewSaleLedgerWeek;
	private SaleLedgerListViewAdapter saleLedgerListViewAdapterWeek;
	private TextView textViewTotalByWeek;
	
	private TabSpec tabByMonth;
	private ListView listViewSaleLedgerMonth;
	private SaleLedgerListViewAdapter saleLedgerListViewAdapterMonth;
	private TextView textViewTotalByMonth;

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
		listViewSaleLedgerDate = (ListView)findViewById(R.id.listViewSaleLedgerDate);
		saleLedgerListViewAdapterDate = new SaleLedgerListViewAdapter(this, Register.getInstance().getLedger().getDaily());
		listViewSaleLedgerDate.setAdapter(saleLedgerListViewAdapterDate);
		textViewTotalByDate = (TextView)findViewById(R.id.textViewTotalByDate);
		textViewTotalByDate.setText(String.format("Total : %.2f", saleLedgerListViewAdapterDate.getTotal()));

		tabByWeek = tabLedger.newTabSpec("tabweek");
		tabByWeek.setContent(R.id.tabweek);
		tabByWeek.setIndicator("By Week");
		tabLedger.addTab(tabByWeek);
		listViewSaleLedgerWeek = (ListView)findViewById(R.id.listViewSaleLedgerWeek);
		saleLedgerListViewAdapterWeek = new SaleLedgerListViewAdapter(this, Register.getInstance().getLedger().getWeek());
		listViewSaleLedgerWeek.setAdapter(saleLedgerListViewAdapterWeek);
		textViewTotalByWeek = (TextView)findViewById(R.id.textViewTotalByWeek);
		textViewTotalByWeek.setText(String.format("Total : %.2f", saleLedgerListViewAdapterWeek.getTotal()));
		
		tabByMonth = tabLedger.newTabSpec("tabmonth");
		tabByMonth.setContent(R.id.tabmonth);
		tabByMonth.setIndicator("By Month");
		tabLedger.addTab(tabByMonth);
		listViewSaleLedgerMonth = (ListView)findViewById(R.id.listViewSaleLedgerMonth);
		saleLedgerListViewAdapterMonth = new SaleLedgerListViewAdapter(this, Register.getInstance().getLedger().getMonth());
		listViewSaleLedgerMonth.setAdapter(saleLedgerListViewAdapterMonth);
		textViewTotalByMonth = (TextView)findViewById(R.id.textViewTotalByMonth);
		textViewTotalByMonth.setText(String.format("Total : %.2f", saleLedgerListViewAdapterMonth.getTotal()));
	}
	
	public void refreshDateListView() {
		saleLedgerListViewAdapterDate.notifyDataSetChanged(Register.getInstance().getLedger().getDaily());
	}
	
	public void refreshWeekListView() {
		saleLedgerListViewAdapterWeek.notifyDataSetChanged(Register.getInstance().getLedger().getWeek());
	}
	
	public void refreshMonthListView() {
		saleLedgerListViewAdapterMonth.notifyDataSetChanged(Register.getInstance().getLedger().getMonth());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ledger_ui, menu);
		return true;
	}

}
