package com.bbaf.mpos.sale.payment.ui;


import android.view.View;
import android.view.View.OnClickListener;

public class PaymentCancelOnClickListener implements OnClickListener {
	
	private PaymentActivity activity;

	private final int PAYMENT_CANCEL = 0;
	
	public PaymentCancelOnClickListener(PaymentActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		activity.setResult(PAYMENT_CANCEL);
		activity.finish();
	}
}
