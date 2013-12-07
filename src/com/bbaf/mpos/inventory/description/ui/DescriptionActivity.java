package com.bbaf.mpos.inventory.description.ui;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;
import com.bbaf.mpos.inventoryAndSale.ui.EditProductActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * @author Sarathit Sangtaweep 5510546182
 */
public class DescriptionActivity extends Activity {
	
	private TextView textViewDescriptionID;
	private TextView textViewDescriptionName;
	private TextView textViewDescriptionPrice;
	private TextView textViewDescriptionCost;
	private TextView textViewDescriptionQuantity;
	private Button buttonDescriptionEdit;
	private Button buttonDescriptionBack;
	
	private static final int EDIT_ACTIVITY_REQUESTCODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description);
		
		final ProductDescription oldProduct = (ProductDescription) getIntent()
				.getSerializableExtra("ProductDescription");
		final Activity activity = (Activity) getIntent()
				.getSerializableExtra("Activity");
		final int oldQuantity = getIntent()
				.getIntExtra("ProductQuantity", 0);
		
		textViewDescriptionID = (TextView)findViewById(R.id.textViewDescriptionID);
		textViewDescriptionID.setText(oldProduct.getId());
		
		textViewDescriptionName = (TextView)findViewById(R.id.textViewDescriptionName);
		textViewDescriptionName.setText(oldProduct.getName());
		
		textViewDescriptionPrice = (TextView)findViewById(R.id.textViewDescriptionPrice);
		textViewDescriptionPrice.setText(String.format("%.2f", oldProduct.getPrice()));
		
		textViewDescriptionCost = (TextView)findViewById(R.id.textViewDescriptionCost);
		textViewDescriptionCost.setText(String.format("%.2f", oldProduct.getCost()));
		
		textViewDescriptionQuantity = (TextView)findViewById(R.id.textViewDescriptionQuantity);
		textViewDescriptionQuantity.setText(String.valueOf(oldQuantity));
		
		buttonDescriptionEdit = (Button)findViewById(R.id.buttonDescriptionEdit);
		buttonDescriptionEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent editActivity = new Intent(getApplicationContext(), EditProductActivity.class);
				
				editActivity.putExtra("ProductDescription", oldProduct);
				editActivity.putExtra("ProductQuantity", oldQuantity);

				startActivityForResult(editActivity, EDIT_ACTIVITY_REQUESTCODE);
			}
		});
		
//		buttonDescriptionBack = (Button)findViewById(R.id.buttonDescriptionBack);
//		buttonDescriptionBack.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				setResult(0);
//				finish();
//			}
//		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.description, menu);
		return true;
	}

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == EDIT_ACTIVITY_REQUESTCODE) {
			setResult(resultCode);
			finish();
		}
	}
}
