package com.bbaf.mpos.sale.payment.ui;

import com.bbaf.mpos.R;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Controller of SuccessPayment view.
 */
public class SuccessPaymentActivity extends Activity {
	
	private TextView textViewChange;
	private Button buttonChangeConfirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_success_payment);
		
		textViewChange = (TextView)findViewById(R.id.textViewChange);
		double cash = getIntent().getDoubleExtra("Cash", 0);
		textViewChange.setText(String.format("%.2f", cash));
		
		buttonChangeConfirm = (Button)findViewById(R.id.buttonChangeComfirm);
		buttonChangeConfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.success_payment, menu);
		return true;
	}
}
