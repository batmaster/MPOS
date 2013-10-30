package com.bbaf.mpos.sale.ui;

import com.bbaf.mpos.ProductDescription;
import com.bbaf.mpos.ProductQuantity;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;

public class SaleTableRow extends TableRow {

	private ProductDescription product;
	private int quantity;
	private CheckBox rowCheckBox;
	private TextView rowProductId;
	private TextView rowProductName;
	private TextView rowUnitPrice;
	private TextView rowQuantity;

	public SaleTableRow(Context context,
			ProductDescription productDescription,
			double unitPrice, int quantity) {
		super(context);

		this.product = productDescription;
		this.quantity = quantity;

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

		rowUnitPrice = new TextView(context);
		rowUnitPrice.setText(String.valueOf(unitPrice));
		rowUnitPrice.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		addView(rowUnitPrice);
		
		rowQuantity = new TextView(context);
		rowQuantity.setText(String.valueOf(quantity));
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
