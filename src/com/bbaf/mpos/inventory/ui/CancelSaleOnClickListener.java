package com.bbaf.mpos.inventory.ui;


import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.bbaf.mpos.FacadeController.Register;

public class CancelSaleOnClickListener implements OnClickListener {

	private ListView listView;
	private InventoryActivity activity;

	public CancelSaleOnClickListener(InventoryActivity activity) {
		this.listView = listView;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		if (Register.getInstance().getSale().getAllList().size() == 0) {
			
		}
		else {
			final AlertDialog.Builder adb = new AlertDialog.Builder(
					activity);
			adb.setTitle("Confirm?");
			adb.setMessage("Cancel Sale?");
			adb.setPositiveButton("Cancel", null);
			adb.setNegativeButton("Confirm", new CancelSaleDialogOnClickListener(activity));
			adb.show();
		}
	}
}
