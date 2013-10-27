package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.inventory.InventoryDBHelper;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;

public class RemoveAllOnClickListener implements OnClickListener {

	private TableLayout tableLayout;
	private InventoryDBHelper dbDAO;
	private InventoryActivity activity;

	public RemoveAllOnClickListener(TableLayout tableLayout,
			InventoryDBHelper dbDAO, InventoryActivity activity) {
		this.tableLayout = tableLayout;
		this.dbDAO = dbDAO;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		final AlertDialog.Builder adb = new AlertDialog.Builder(
				activity);
		adb.setTitle("Confirm?");
		adb.setMessage("Plese Confirm");
		adb.setNegativeButton("Cancel", null);
		adb.setPositiveButton("Ok", new DialogOnClickListener(tableLayout,
				dbDAO, activity));
		adb.show();
	}

}
