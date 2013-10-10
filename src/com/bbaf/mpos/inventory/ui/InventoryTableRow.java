package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.ProductDescription;
import com.bbaf.mpos.ProductQuantity;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;

public class InventoryTableRow extends TableRow {

	private ProductDescription product;
	private ProductQuantity quantity;
	private CheckBox rowCheckBox;
	private TextView rowProductId;
	private TextView rowProductName;
	private TextView rowQuantity;

	public InventoryTableRow(Context context,
			ProductDescription productDescription,
			ProductQuantity productQuantity) {
		super(context);

		this.product = productDescription;
		this.quantity = productQuantity;

		rowCheckBox = new CheckBox(context);
		addView(rowCheckBox);

		rowProductId = new TextView(context);
		rowProductId.setText(String.valueOf(product.getId()));
		rowProductId.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		addView(rowProductId);

		rowProductName = new TextView(context);
		rowProductName.setText(product.getName());
		rowProductName.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		addView(rowProductName);

		rowQuantity = new TextView(context);
		rowQuantity.setText(String.valueOf(quantity.getQuantity()));
		rowQuantity.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		addView(rowQuantity);
	}

	public boolean isChecked() {
		return rowCheckBox.isChecked();
	}

	public ProductDescription getProduct() {
		return product;
	}

}
