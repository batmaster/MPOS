package com.bbaf.mpos.sale.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TableRow;
import android.widget.TextView;

public class SaleTableHead extends TableRow {

	private TextView headCheckBox;
	private TextView headProductId;
	private TextView headProductName;
	private TextView unitPrice;
	private TextView headQuantity;

	public SaleTableHead(Context context) {
		super(context);

		headCheckBox = new TextView(context);
		headCheckBox.setPadding(5, 5, 20, 5);
		addView(headCheckBox);

		headProductId = new TextView(context);
		headProductId.setText("ID");
		headProductId.setTypeface(null, Typeface.BOLD);
		headProductId.setPadding(0, 0, 20, 0);
		headProductId.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		addView(headProductId);

		headProductName = new TextView(context);
		headProductName.setText("ProductName");
		headProductName.setTypeface(null, Typeface.BOLD);
		headProductName.setPadding(0, 0, 20, 0);
		headProductName.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		addView(headProductName);
		
		unitPrice = new TextView(context);
		unitPrice.setText("UnitPrice");
		unitPrice.setTypeface(null, Typeface.BOLD);
		unitPrice.setPadding(0, 0, 20, 0);
		unitPrice.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		addView(unitPrice);

		headQuantity = new TextView(context);
		headQuantity.setText("Qt.");
		headQuantity.setTypeface(null, Typeface.BOLD);
		headQuantity.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		addView(headQuantity);
	}

}
