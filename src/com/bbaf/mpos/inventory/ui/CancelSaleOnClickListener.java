package com.bbaf.mpos.inventory.ui;


import com.bbaf.mpos.FacadeController.Store;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TableLayout;

public class CancelSaleOnClickListener implements OnClickListener {

	private ListView listView;
	private InventoryActivity2 activity;

	public CancelSaleOnClickListener(InventoryActivity2 activity) {
		this.listView = listView;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		final AlertDialog.Builder adb = new AlertDialog.Builder(
				activity);
		adb.setTitle("Confirm?");
		adb.setMessage("Cancel Sale?");
		adb.setNegativeButton("Cancel", null);
		adb.setPositiveButton("Ok", new CancelSaleDialogOnClickListener(activity));
		adb.show();
	}
}
