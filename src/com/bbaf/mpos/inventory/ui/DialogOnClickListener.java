package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.FacadeController.Store;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ListView;
import android.widget.TableLayout;

public class DialogOnClickListener implements OnClickListener {

	private ListView listViewInventory;
	private InventoryActivity2 activity;
	
	public DialogOnClickListener(ListView listViewInventory, InventoryActivity2 activity) {
		this.listViewInventory = listViewInventory;
		this.activity = activity;
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		for (int i = 0; i < listViewInventory.getChildCount(); i++) {
			InventoryListRow row = (InventoryListRow)listViewInventory.getChildAt(i);

			if (row.isChecked()) {
				Store.getInstance().removeProduct(row.getProduct());
			}
		}
		activity.refreshIntenvoryListView();
	}

}
