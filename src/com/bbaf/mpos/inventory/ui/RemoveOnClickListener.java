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
	private Activity activity;
	
	public RemoveOnClickListener(TableLayout tableLayout,InventoryDBHelper dbDAO,Activity activity) {
		this.tableLayout = tableLayout;
		this.dbDAO = dbDAO;
		this.activity = activity;
	}
	
	@Override
	public void onClick(View v) {
		for (int i = 1; i < tableLayout.getChildCount(); i++) {
			Log.d("rem", "loop");
			InventoryTableRow row = (InventoryTableRow) tableLayout
					.getChildAt(i);

			if (row.isChecked()) {
				dbDAO.removeProduct(row.getProduct());
			}
		}
		// tabHost.invalidate();
		// tabHost.refreshDrawableState();
		((InventoryActivity)activity).refreshTable();
	}

}
