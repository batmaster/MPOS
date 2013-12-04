package com.bbaf.mpos.inventory.ui;


import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.FacadeController.Store;
import com.bbaf.mpos.sale.payment.ui.PaymentActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

public class PaymentOnClickListener implements OnClickListener {

	private InventoryActivity2 activity;
	
	private static final int PAYMENT_ACTIVITY_REQUESTCODE = 2;

	public PaymentOnClickListener(InventoryActivity2 activity) {
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
