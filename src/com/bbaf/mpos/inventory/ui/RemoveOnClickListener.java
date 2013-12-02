package com.bbaf.mpos.inventory.ui;


import com.bbaf.mpos.FacadeController.Store;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;

public class RemoveOnClickListener implements OnClickListener {

	private TableLayout tableLayout;
	private InventoryActivity2 activity;

	public RemoveOnClickListener(TableLayout tableLayout, InventoryActivity2 activity) {
		this.tableLayout = tableLayout;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		for (int i = 1; i < tableLayout.getChildCount(); i++) {
			InventoryTableRow row = (InventoryTableRow) tableLayout
					.getChildAt(i);

			if (row.isChecked()) {
				Store.getInstance().removeProduct(row.getProduct());
			}
		}
//		activity.refreshIntenvoryTable();
	}

}
