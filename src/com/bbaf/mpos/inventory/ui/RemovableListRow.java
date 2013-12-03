package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.ProductDescription.ProductDescription;

import android.content.Context;
import android.widget.RelativeLayout;

public abstract class RemovableListRow extends RelativeLayout {

	public RemovableListRow(Context context) {
		super(context);
	}
	
	public abstract boolean isChecked();
	
	public abstract ProductDescription getProduct();

	public abstract void remove();

}
