package com.bbaf.mpos.inventory.ui;

import java.util.ArrayList;

import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.sale.SaleLineItem;
import com.bbaf.mpos.sale.payment.ui.PaymentActivity;
import com.bbaf.mpos.sale.payment.ui.SuccessPaymentActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class PaymentConfirmOnClickListener implements OnClickListener {
	
	private PaymentActivity activity;
	
	private final int PAYMENT_SUCCESS = 1;
	
	public PaymentConfirmOnClickListener(PaymentActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View arg0) {
		if (activity.getCash() >= Register.getInstance().getTotal()) {
			ArrayList<SaleLineItem> sli = Register.getInstance().getAllSaleLineItemList();
			for(int i = 0 ;i < sli.size();i++){
				Register.getInstance().decrease(sli.get(i).getProductDescription().getId(), sli.get(i).getQuantity());
			}
			
			if (sli.size() != 0) {
				Register.getInstance().getLedger().record(Register.getInstance().getSale());
				Toast.makeText(activity.getApplicationContext(), "Sale ended with " + sli.size() + " line item(s).", Toast.LENGTH_SHORT).show();
			}
			else {
				Toast.makeText(activity.getApplicationContext(), "Sale cancelled ", Toast.LENGTH_SHORT).show();
			}
			
			Intent successPaymentActivity = new Intent(activity, SuccessPaymentActivity.class);
			successPaymentActivity.putExtra("Cash", activity.getCash() - Register.getInstance().getTotal());
			activity.startActivity(successPaymentActivity);
			
			Register.getInstance().endSale();
			Register.getInstance().startSale();
			
			activity.setResult(PAYMENT_SUCCESS);
			activity.finish();
		}
		else {
			Toast.makeText(activity.getApplicationContext(), String.format("Need more %.2f", Register.getInstance().getTotal() - activity.getCash()), Toast.LENGTH_SHORT).show();
		}
	}
}
