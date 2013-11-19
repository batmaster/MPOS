package com.bbaf.mpos.inventory.ui;


import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;

public class RemoveAllOnClickListener implements OnClickListener {

	private TableLayout tableLayout;
	private InventoryActivity activity;

	public RemoveAllOnClickListener(TableLayout tableLayout, InventoryActivity activity) {
		this.tableLayout = tableLayout;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		final AlertDialog.Builder adb = new AlertDialog.Builder(
				activity);
		adb.setTitle("Confirm?");
		adb.setMessage("Plese Confirm");
		adb.setNegativeButton("Cancel", null);
		adb.setPositiveButton("Ok", new DialogOnClickListener(tableLayout, activity));
		adb.show();
	}

}
