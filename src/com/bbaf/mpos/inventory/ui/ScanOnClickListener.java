package com.bbaf.mpos.inventory.ui;

import com.google.zxing.integration.android.IntentIntegrator;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class ScanOnClickListener implements OnClickListener {

	private Activity activity;

	public ScanOnClickListener(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
		scanIntegrator.initiateScan();
	}

}
