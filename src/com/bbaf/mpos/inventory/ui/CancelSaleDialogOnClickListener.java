package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.FacadeController.Store;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ListView;
import android.widget.TableLayout;

public class CancelSaleDialogOnClickListener implements OnClickListener {

	private InventoryActivity2 activity;
	
	public CancelSaleDialogOnClickListener(InventoryActivity2 activity) {
		this.activity = activity;
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		Register.getInstance().removeAllItem();
		activity.clearSaleTab();
		activity.refreshSaleListView();
	}

}
