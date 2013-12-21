package com.bbaf.mpos.inventoryAndSale.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.bbaf.mpos.FacadeController.Register;

/**
 * ActionListener of CancelSale button in Sale view.
 * @author Sarathit Sangtaweep 5510546182, Poramet Homprakob 5510546077
 */
public class CancelSaleDialogOnClickListener implements OnClickListener {

	private InventoryandSaleActivity activity;
	
	/**
	 * Constructor, use calling activity as a context.
	 * @param activity calling activity
	 */
	public CancelSaleDialogOnClickListener(InventoryandSaleActivity activity) {
		this.activity = activity;
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		Register.getInstance().removeAllItem();
		activity.clearSaleTab();
		activity.refreshSaleListView();
	}
}
