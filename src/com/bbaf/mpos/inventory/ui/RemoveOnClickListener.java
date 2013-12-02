package com.bbaf.mpos.inventory.ui;


import com.bbaf.mpos.FacadeController.Store;
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
		for (int i = 0; i < listViewInventory.getChildCount(); i++) {
			InventoryListRow row = (InventoryListRow)listViewInventory.getChildAt(i);

			if (row.isChecked()) {
				Store.getInstance().removeProduct(row.getProduct());
			}
		}
		activity.refreshIntenvoryTable();
	}

}
