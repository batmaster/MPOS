package com.bbaf.mpos.sale.payment.ui;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * ActionListener of Cancel in Payment view.
 */
public class PaymentCancelOnClickListener implements OnClickListener {
	
	private PaymentActivity activity;

	private final int PAYMENT_CANCEL = 0;
	
	/**
	 * Constructor, use calling activity
	 * @param activity calling activity
	 */
	public PaymentCancelOnClickListener(PaymentActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		activity.setResult(PAYMENT_CANCEL);
		activity.finish();
	}
}
