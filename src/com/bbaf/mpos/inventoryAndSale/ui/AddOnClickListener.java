package com.bbaf.mpos.inventoryAndSale.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.ProductDescription.ProductDescription;

/**
 * ActionListener of Add button in Inventory view.
 */
public class AddOnClickListener implements OnClickListener {

	private AddProductActivity activity;
	
	private static final int ADD_SUCCESS = 1;
	
	/**
	 * Constructor, use calling activity as a context.
	 * @param activity calling activity
	 */
	public AddOnClickListener(AddProductActivity activity) {
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
			if (Register.getInstance().getInventory().getProduct(id) == null) {
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
				long row = Register.getInstance().getInventory().addProduct(product,quantity);
				Toast.makeText(
						activity.getApplicationContext(),
						String.format(
								"Product add to row %d successfully.",
								row), Toast.LENGTH_SHORT).show();
				activity.setResult(ADD_SUCCESS);
				
				editTextProductId.setText("");
				editTextProductName.setText("");
				editTextPrice.setText("");
				editTextCost.setText("");
				editTextQuantity.setText("");
				editTextProductId.requestFocus();
				//
			}
			else {
				Toast.makeText(activity.getApplicationContext(), String.format("Product ID %s is already added", id), Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(activity.getApplicationContext(),
					"Product ID must not be empty.", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
