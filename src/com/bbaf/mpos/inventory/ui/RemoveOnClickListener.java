package com.bbaf.mpos.inventory.ui;


import com.bbaf.mpos.FacadeController.Store;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TableLayout;

public class RemoveOnClickListener implements OnClickListener {

	private ListView listViewInventory;
	private InventoryActivity2 activity;

	public RemoveOnClickListener(ListView listViewInventory, InventoryActivity2 activity) {
		this.listViewInventory = listViewInventory;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		final AlertDialog.Builder adb = new AlertDialog.Builder(
				activity);
		adb.setTitle("Confirm?");
		adb.setMessage("Plese Confirm");
		adb.setNegativeButton("Cancel", null);
		adb.setPositiveButton("Ok", new DialogOnClickListener(listViewInventory, activity));
		adb.show();
	}
}
