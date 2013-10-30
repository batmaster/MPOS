package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.ProductDescription;
import com.bbaf.mpos.ProductQuantity;
import com.bbaf.mpos.R;
import com.bbaf.mpos.inventory.InventoryDBHelper;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProductActivity extends Activity {

	private EditText editTextProductId;
	private EditText editTextProductName;
	private EditText editTextPrice;
	private EditText editTextCost;
	private EditText editTextQuantity;
	private Button buttonScan;
	private Button buttonSave;
	private Button buttonCancel;

	private InventoryDBHelper dbHelper;
	
	private static final int SCANNER_ACTIVITY_REQUESTCODE = 49374;
	private static final int EDIT_CANCEL = 0;
	private static final int EDIT_SUCCESS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_product);

		dbHelper = new InventoryDBHelper(this);
		final ProductDescription oldProduct = (ProductDescription) getIntent()
				.getSerializableExtra("ProductDescription");
		final int oldQuantity = getIntent()
				.getIntExtra("ProductQuantity", 0);

		editTextProductId = (EditText) findViewById(R.id.edit_editTextProductId);
		editTextProductId.setText(String.valueOf(oldProduct.getId()));
		editTextProductName = (EditText) findViewById(R.id.edit_editTextProductName);
		editTextProductName.setText(String.valueOf(oldProduct.getName()));
		editTextPrice = (EditText) findViewById(R.id.edit_editTextPrice);
		editTextPrice.setText(String.valueOf(oldProduct.getPrice()));
		editTextCost = (EditText) findViewById(R.id.edit_editTextCost);
		editTextCost.setText(String.valueOf(oldProduct.getCost()));
		editTextQuantity = (EditText) findViewById(R.id.edit_editTextQuantity);
		editTextQuantity.setText(String.valueOf(oldQuantity));
		buttonScan = (Button) findViewById(R.id.buttonScan);
		buttonScan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				IntentIntegrator scanIntegrator = new IntentIntegrator(
						EditProductActivity.this);
				scanIntegrator.initiateScan();
			}
		});
		
		buttonSave = (Button) findViewById(R.id.buttonSave);
		buttonSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String id = editTextProductId.getText().toString();
				if (id.equals("")) {
					if (dbHelper.getProduct(id) != null) {
						Toast.makeText(getApplicationContext(),
								"Product ID must not be empty.", Toast.LENGTH_SHORT)
								.show();
					}
				}
				else {
					if (!id.equals(oldProduct.getId())) {
						if (dbHelper.getProduct(id) != null) {
							Toast.makeText(
									getApplicationContext(),
									String.format("Product : %s is already added.",
											id), Toast.LENGTH_SHORT).show();
						}
						// duplicated
						else {
							String name = editTextProductName.getText().toString();
							double price = Double.parseDouble(editTextPrice
									.getText().toString());
							double cost = Double.parseDouble(editTextCost.getText()
									.toString());
							ProductDescription newProduct = new ProductDescription(
									id, name, price, cost);
							dbHelper.editProduct(oldProduct, newProduct);

							int quantity = Integer.parseInt(editTextQuantity
									.getText().toString());
							dbHelper.editQuantity(oldProduct, newProduct, quantity);

							setResult(EDIT_SUCCESS);
							finish();
						}
					}
					// duplicated
					else {
						String name = editTextProductName.getText().toString();
						double price = Double.parseDouble(editTextPrice
								.getText().toString());
						double cost = Double.parseDouble(editTextCost.getText()
								.toString());
						ProductDescription newProduct = new ProductDescription(
								id, name, price, cost);
						dbHelper.editProduct(oldProduct, newProduct);

						int quantity = Integer.parseInt(editTextQuantity
								.getText().toString());
						dbHelper.editQuantity(oldProduct, newProduct, quantity);

						setResult(EDIT_SUCCESS);
						finish();
					}
					
				}
			}
		});

		buttonCancel = (Button) findViewById(R.id.buttonCancel);
		buttonCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(EDIT_SUCCESS);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_product, menu);
		return true;
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == SCANNER_ACTIVITY_REQUESTCODE) {
            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            // bat: although scanner is canceled, scanningResult is not null but scanningResult.getContents()
        	if (scanningResult != null) {
        		editTextProductId.setText(scanningResult.getContents());
        		editTextProductName.requestFocus();
        	}
        	else {
        	    Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT).show();
        	}
		}
	}

}
