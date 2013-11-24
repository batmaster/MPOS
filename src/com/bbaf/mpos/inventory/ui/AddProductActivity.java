package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.R;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddProductActivity extends Activity {
	
	private EditText editTextProductId;
	private EditText editTextProductName;
	private EditText editTextCost;
	private EditText editTextPrice;
	private EditText editTextQuantity;
	private Button buttonAddX;
	private Button buttonClearX;
	private Button buttonCancelX;
	
	private static final int ADD_CANCEL = 0;

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
		
		buttonClearX = (Button)findViewById(R.id.buttonClearX);
		buttonClearX.setOnClickListener(new ClearOnClickListener(this));
		
		buttonCancelX = (Button)findViewById(R.id.buttonCancelX);
		buttonCancelX.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		setResult(ADD_CANCEL);
	}
	
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
