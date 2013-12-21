package com.bbaf.mpos.sale.payment.ui;

import com.bbaf.mpos.R;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;
import com.bbaf.mpos.FacadeController.Register;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity for showing Payment. Controller of Payment view.
 * @author Sarathit Sangtaweep 5510546182, Poramet Homprakob 5510546077
 */
public class PaymentActivity extends Activity {
	
	private TextView textViewPaymentTotal;
	private EditText editTextPay;
	private Button buttonPaymentConfirm;
	private Button buttonPaymentCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment);
		
		textViewPaymentTotal = (TextView)findViewById(R.id.textViewPaymentTotal);
		textViewPaymentTotal.setText(String.format("%.2f", Register.getInstance().getTotal()));
		
		editTextPay = (EditText)findViewById(R.id.editTextPay);
		
		buttonPaymentConfirm = (Button)findViewById(R.id.buttonPaymentComfirm);
		buttonPaymentConfirm.setOnClickListener(new PaymentConfirmOnClickListener(this));
		
		buttonPaymentCancel = (Button)findViewById(R.id.buttonPaymentCancel);
		buttonPaymentCancel.setOnClickListener(new PaymentCancelOnClickListener(this));
	}
	
	/**
	 * Return number in EditText.
	 * @return number in EditText
	 */
	public double getCash() {
		return editTextPay.getText().toString().equals("") ? 0 : Double.parseDouble(editTextPay.getText().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.payment, menu);
		return true;
	}
}
