package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.FacadeController.Store;
import com.bbaf.mpos.ProductDescription.ProductDescription;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class AddOnClickListener implements OnClickListener {

	private InventoryActivity activity;
	
	public AddOnClickListener(InventoryActivity activity) {
		this.activity = activity;
	}
	
	@Override
	public void onClick(View v) {
		EditText[] text = activity.getAllEditText();
		EditText editTextProductId = text[0];
		EditText editTextProductName = text[1];
		EditText editTextPrice = text[2];
		EditText editTextCost = text[3];
		EditText editTextQuantity = text[4];
		String id = editTextProductId.getText().toString();
		if (!id.equals("")) {
			if (Store.getInstance().getProduct(id) != null) {
				Toast.makeText(
						activity.getApplicationContext(),
						String.format("Product : %s is already added.",
								id), Toast.LENGTH_SHORT).show();
			} else {
				String name = editTextProductName.getText().toString();
				String priceText = editTextPrice.getText().toString();
				String costText = editTextCost.getText().toString();
				String quantityText = editTextQuantity.getText()
						.toString();

				double price = Double.parseDouble(priceText.equals("") ? "0"
						: priceText);
				double cost = Double.parseDouble(costText.equals("") ? "0"
						: costText);
				int quantity = Integer.parseInt(quantityText.equals("") ? "1"
						: quantityText);
				ProductDescription product = new ProductDescription(id,
						name, price, cost);

				long row = Store.getInstance().addProduct(product);
				Store.getInstance().addQuantity(product, quantity);

				Toast.makeText(
						activity.getApplicationContext(),
						String.format(
								"Product add to row %d successfully.",
								row), Toast.LENGTH_SHORT).show();

				// bat: moved from clear() in InventoryActivity
				editTextProductId.setText("");
				editTextProductName.setText("");
				editTextPrice.setText("");
				editTextCost.setText("");
				editTextQuantity.setText("");
				editTextProductId.requestFocus();
				//
				
				activity.refreshTable();
			}
		} else {
			Toast.makeText(activity.getApplicationContext(),
					"Product ID must not be empty.", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
