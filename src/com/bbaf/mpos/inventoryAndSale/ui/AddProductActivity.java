package com.bbaf.mpos.inventoryAndSale.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Controller of AddProductActivity.
 */
public class AddProductActivity extends Activity {
	
	private EditText editTextProductId;
	private EditText editTextProductName;
	private EditText editTextCost;
	private EditText editTextPrice;
	private EditText editTextQuantity;
	private Button buttonAddX;
	private Button buttonScanX;
	private Button buttonCancelX;
	
	private static final int ADD_CANCEL = 0;
	private static final int SCANNER_ACTIVITY_REQUESTCODE = 49374;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product);
		
		editTextProductId = (EditText)findViewById(R.id.editTextProductId2);
		editTextProductName = (EditText)findViewById(R.id.editTextProductName2);
		editTextPrice = (EditText)findViewById(R.id.editTextPrice2);
		editTextCost = (EditText)findViewById(R.id.editTextCost2);
		editTextQuantity = (EditText)findViewById(R.id.editTextQuantity3);
		
		buttonAddX = (Button)findViewById(R.id.buttonAddX);
		buttonAddX.setOnClickListener(new AddOnClickListener(this));
		
		buttonScanX = (Button)findViewById(R.id.buttonScanX);
		buttonScanX.setOnClickListener(new ScanOnClickListener(this));
		
		buttonCancelX = (Button)findViewById(R.id.buttonCancelX);
		buttonCancelX.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		setResult(ADD_CANCEL);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// bat: for tracking whether from which Activity and what is the result
		Log.d("result", "requestCode " + requestCode);
		Log.d("result", "resultCode " + resultCode);
		//
		if (requestCode == SCANNER_ACTIVITY_REQUESTCODE) {
			IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        	if (scanningResult != null) {
        		if (Register.getInstance().getInventory().getProduct(scanningResult.getContents()) == null) {
        			editTextProductId.setText(scanningResult.getContents());
	        		editTextQuantity.requestFocus();
        		}
        		else {
        			Toast.makeText(getApplicationContext(),"ID has been registered already.", Toast.LENGTH_SHORT).show();
        		}
        	}
        	else {
        	    Toast.makeText(getApplicationContext(),"No scanned data received!", Toast.LENGTH_SHORT).show();
        	}
		}
	}
		
	/**
	 * Return all EditText in activity.
	 * @return all EditText in activity
	 */
	public EditText[] getAllEditText() {
		return new EditText[] {editTextProductId, editTextProductName, editTextPrice, editTextCost, editTextQuantity};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_product, menu);
		return true;
	}
}
