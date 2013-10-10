package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.inventory.InventoryDBHelper;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;

public class RemoveOnClickListener implements OnClickListener {

	private TableLayout tableLayout;
	private InventoryDBHelper dbDAO;
	private InventoryActivity activity;

	public RemoveOnClickListener(TableLayout tableLayout,
			InventoryDBHelper dbDAO, InventoryActivity activity) {
		this.tableLayout = tableLayout;
		this.dbDAO = dbDAO;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		for (int i = 1; i < tableLayout.getChildCount(); i++) {
			InventoryTableRow row = (InventoryTableRow) tableLayout
					.getChildAt(i);

			if (row.isChecked()) {
				dbDAO.removeProduct(row.getProduct());
			}
		}
		activity.refreshTable();
	}

}
