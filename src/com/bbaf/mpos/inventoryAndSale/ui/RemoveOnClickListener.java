package com.bbaf.mpos.inventoryAndSale.ui;


import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class RemoveOnClickListener implements OnClickListener {

	private ListView listView;
	private InventoryandSaleActivity activity;

	public RemoveOnClickListener(ListView listView, InventoryandSaleActivity activity) {
		this.listView = listView;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		boolean hasChecked = false;
		for (int i = 0; i < listView.getChildCount(); i++) {
			RemovableListRow row = (RemovableListRow)listView.getChildAt(i);

			if (row.isChecked()) {
				hasChecked = true;
				break;
			}
		}
		
		if (hasChecked) {
			final AlertDialog.Builder adb = new AlertDialog.Builder(
					activity);
			adb.setTitle("Confirm?");
			adb.setMessage("Remove item(s)?");
			adb.setPositiveButton("Cancel", null);
			adb.setNegativeButton("Confirm", new RemoveDialogOnClickListener(listView, activity));
			adb.show();
		}
	}
}
