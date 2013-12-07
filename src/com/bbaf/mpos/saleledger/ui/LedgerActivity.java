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
import com.bbaf.mpos.saleledger.SaleLedger;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.TableLayout;

public class LedgerActivity extends Activity {
	
	private TabHost tabLedger;
	private TabSpec tabByDate;
	private TextView textViewDate;
	private ListView listViewSaleLedgerDate;
	private SaleLedgerListViewAdapter saleLedgerListViewAdapterDate;
	private TextView textViewTotalByDate;
	private Button buttonDateNext;
	private Button buttonDatePrevious;
	
	private TabSpec tabByWeek;
	private TextView textViewWeek;
	private ListView listViewSaleLedgerWeek;
	private SaleLedgerListViewAdapter saleLedgerListViewAdapterWeek;
	private TextView textViewTotalByWeek;
	private Button buttonWeekNext;
	private Button buttonWeekPrevious;
	
	private TabSpec tabByMonth;
	private TextView textViewMonth;
	private ListView listViewSaleLedgerMonth;
	private SaleLedgerListViewAdapter saleLedgerListViewAdapterMonth;
	private TextView textViewTotalByMonth;
	private Button buttonMonthNext;
	private Button buttonMonthPrevious;

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
		textViewDate = (TextView)findViewById(R.id.textViewDate);
		listViewSaleLedgerDate = (ListView)findViewById(R.id.listViewSaleLedgerDate);
		saleLedgerListViewAdapterDate = new SaleLedgerListViewAdapter(this, Register.getInstance().getLedger().getDaily());
		listViewSaleLedgerDate.setAdapter(saleLedgerListViewAdapterDate);
		textViewTotalByDate = (TextView)findViewById(R.id.textViewTotalByDate);
		buttonDateNext = (Button)findViewById(R.id.buttonDateNext);
		buttonDateNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Register.getInstance().getLedger().nextDaily();
				refreshDateListView();
			}
		});
		buttonDatePrevious = (Button)findViewById(R.id.buttonDatePrevious);
		buttonDatePrevious.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Register.getInstance().getLedger().prevDaily();
				refreshDateListView();
			}
		});
		
		tabByWeek = tabLedger.newTabSpec("tabweek");
		tabByWeek.setContent(R.id.tabweek);
		tabByWeek.setIndicator("By Week");
		tabLedger.addTab(tabByWeek);
		textViewWeek = (TextView)findViewById(R.id.textViewWeek);
		listViewSaleLedgerWeek = (ListView)findViewById(R.id.listViewSaleLedgerWeek);
		saleLedgerListViewAdapterWeek = new SaleLedgerListViewAdapter(this, Register.getInstance().getLedger().getWeek());
		listViewSaleLedgerWeek.setAdapter(saleLedgerListViewAdapterWeek);
		textViewTotalByWeek = (TextView)findViewById(R.id.textViewTotalByWeek);
		buttonWeekNext = (Button)findViewById(R.id.buttonWeekNext);
		buttonWeekNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Register.getInstance().getLedger().nextWeek();
				refreshWeekListView();
			}
		});
		buttonWeekPrevious = (Button)findViewById(R.id.buttonWeekPrevious);
		buttonWeekPrevious.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Register.getInstance().getLedger().prevWeek();
				refreshWeekListView();
			}
		});
		
		tabByMonth = tabLedger.newTabSpec("tabmonth");
		tabByMonth.setContent(R.id.tabmonth);
		tabByMonth.setIndicator("By Month");
		tabLedger.addTab(tabByMonth);
		textViewMonth = (TextView)findViewById(R.id.textViewMonth);
		listViewSaleLedgerMonth = (ListView)findViewById(R.id.listViewSaleLedgerMonth);
		saleLedgerListViewAdapterMonth = new SaleLedgerListViewAdapter(this, Register.getInstance().getLedger().getMonth());
		listViewSaleLedgerMonth.setAdapter(saleLedgerListViewAdapterMonth);
		textViewTotalByMonth = (TextView)findViewById(R.id.textViewTotalByMonth);
		buttonMonthNext = (Button)findViewById(R.id.buttonMonthNext);
		buttonMonthNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Register.getInstance().getLedger().nextMonth();
				refreshMonthListView();
			}
		});
		buttonMonthPrevious = (Button)findViewById(R.id.buttonMonthPrevious);
		buttonMonthPrevious.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Register.getInstance().getLedger().prevMonth();
				refreshMonthListView();
			}
		});
		
		refreshDateListView();
		refreshMonthListView();
		refreshWeekListView();
	}
	
	public void refreshDateListView() {
		saleLedgerListViewAdapterDate.notifyDataSetChanged(Register.getInstance().getLedger().getDaily());
		textViewDate.setText(Register.getInstance().getLedger().getDateS());
		textViewTotalByDate.setText(String.format("Total : %.2f", saleLedgerListViewAdapterDate.getTotal()));
	}
	
	public void refreshWeekListView() {
		saleLedgerListViewAdapterWeek.notifyDataSetChanged(Register.getInstance().getLedger().getWeek());
		textViewWeek.setText(Register.getInstance().getLedger().getWeekS());
		textViewTotalByWeek.setText(String.format("Total : %.2f", saleLedgerListViewAdapterWeek.getTotal()));
	}
	
	public void refreshMonthListView() {
		saleLedgerListViewAdapterMonth.notifyDataSetChanged(Register.getInstance().getLedger().getMonth());
		textViewMonth.setText(Register.getInstance().getLedger().getMonthS());
		textViewTotalByMonth.setText(String.format("Total : %.2f", saleLedgerListViewAdapterMonth.getTotal()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ledger_ui, menu);
		return true;
	}

}
