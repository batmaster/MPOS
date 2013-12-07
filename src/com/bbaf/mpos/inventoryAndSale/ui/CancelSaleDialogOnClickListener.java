package com.bbaf.mpos.inventoryAndSale.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.bbaf.mpos.FacadeController.Register;

public class CancelSaleDialogOnClickListener implements OnClickListener {

	private InventoryandSaleActivity activity;
	
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
