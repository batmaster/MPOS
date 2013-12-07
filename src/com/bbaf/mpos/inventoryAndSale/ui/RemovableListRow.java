package com.bbaf.mpos.inventoryAndSale.ui;

import android.content.Context;
import android.widget.RelativeLayout;

import com.bbaf.mpos.ProductDescription.ProductDescription;

public abstract class RemovableListRow extends RelativeLayout {

	public RemovableListRow(Context context) {
		super(context);
	}
	
	public abstract boolean isChecked();
	
	public abstract ProductDescription getProduct();

	public abstract void remove();

}
