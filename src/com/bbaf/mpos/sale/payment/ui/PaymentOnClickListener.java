package com.bbaf.mpos.sale.payment.ui;


import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.inventory.ui.InventoryActivity;

public class PaymentOnClickListener implements OnClickListener {

	private InventoryActivity activity;
	
	private static final int PAYMENT_ACTIVITY_REQUESTCODE = 2;

	public PaymentOnClickListener(InventoryActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		if (Register.getInstance().getSale().getAllList().size() == 0) {
			Toast.makeText(activity.getApplicationContext(), "Add item first.", Toast.LENGTH_SHORT).show();
		}
		else {
			Intent paymentActivity = new Intent(activity.getApplicationContext(), PaymentActivity.class);
			activity.startActivityForResult(paymentActivity, PAYMENT_ACTIVITY_REQUESTCODE);
		}
	}
}
