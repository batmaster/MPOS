package com.bbaf.mpos.inventory.ui;

import com.google.zxing.integration.android.IntentIntegrator;

import android.view.View;
import android.view.View.OnClickListener;

public class ScanOnClickListener implements OnClickListener {

	private InventoryActivity activity;

	public ScanOnClickListener(InventoryActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
		scanIntegrator.initiateScan();
	}

}
