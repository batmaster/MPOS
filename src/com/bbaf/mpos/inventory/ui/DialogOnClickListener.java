package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.FacadeController.Store;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ListView;
import android.widget.TableLayout;

public class DialogOnClickListener implements OnClickListener {

	private ListView listView;
	private InventoryActivity2 activity;
	
	public DialogOnClickListener(ListView listView, InventoryActivity2 activity) {
		this.listView = listView;
		this.activity = activity;
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		for (int i = 0; i < listView.getChildCount(); i++) {
			RemovableListRow row = (RemovableListRow)listView.getChildAt(i);

			if (row.isChecked()) {
				row.remove();
			}
		}
		activity.refreshIntenvoryListView();
		activity.refreshSaleListView();
	}

}
