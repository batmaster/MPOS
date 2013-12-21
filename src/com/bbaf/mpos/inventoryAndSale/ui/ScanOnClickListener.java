package com.bbaf.mpos.inventoryAndSale.ui;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.zxing.integration.android.IntentIntegrator;

/**
 * ActionListener of Scan button.
 * @author Poramet Homprakob 5510546077
 */
public class ScanOnClickListener implements OnClickListener {

	private Activity activity;

	/**
	 * Constructor, use calling activity.
	 * @param activity calling activity
	 */
	public ScanOnClickListener(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
		scanIntegrator.initiateScan();
	}
}
