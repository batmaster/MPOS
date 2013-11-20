package com.bbaf.mpos.saleledger.ui;

import com.bbaf.mpos.R;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class LedgerUIActivity extends Activity {
	
	private TabHost tabLedger;
	private TabSpec tabByDate;
	private TabSpec tabByWeek;
	private TabSpec tabByMonth;

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
		

		tabByWeek = tabLedger.newTabSpec("tabweek");
		tabByWeek.setContent(R.id.tabweek);
		tabByWeek.setIndicator("By Week");
		tabLedger.addTab(tabByWeek);
		
		tabByMonth = tabLedger.newTabSpec("tabmonth");
		tabByMonth.setContent(R.id.tabmonth);
		tabByMonth.setIndicator("By Month");
		tabLedger.addTab(tabByMonth);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ledger_ui, menu);
		return true;
	}

}
