package com.bbaf.mpos.inventoryAndSale.ui;

import android.content.Context;
import android.widget.RelativeLayout;

import com.bbaf.mpos.ProductDescription.ProductDescription;

/**
 * Abstract of ListRow which has CheckBox and can use description in itself to remove data in database.
 */
public abstract class RemovableListRow extends RelativeLayout {

	/**
	 * Constructor, use activity context to create ListRow.
	 * @param context context of calling activity
	 */
	public RemovableListRow(Context context) {
		super(context);
	}
	
	/**
	 * Return is the CheckBox in itself checked.
	 * @return true if CheckBox is check, otherwise false
	 */
	public abstract boolean isChecked();
	
	/**
	 * Return ProductDescription which is stored in itself
	 * @return ProductDesCription object
	 */
	public abstract ProductDescription getProduct();

	/**
	 * Remove data in database by using information from attribute
	 */
	public abstract void remove();
}
