package com.bbaf.mpos.inventory.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.ProductDescription.ProductDescription;

public class ATSOnClickListener implements OnClickListener {

	private ListView listViewInventory;
	private InventoryActivity activity;

	public ATSOnClickListener(ListView listViewInventory, InventoryActivity activity) {
		this.listViewInventory = listViewInventory;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		int count = 0;
		for (int i = 0; i < listViewInventory.getChildCount(); i++) {
			try {
				InventoryListRow row = (InventoryListRow)listViewInventory.getChildAt(i);
				
				if (row.isChecked()) {
					ProductDescription product = row.getProduct();
					Register.getInstance().addItem(product, 1);
					count++;
				}
			} catch (ClassCastException e) {
			}
		}
		TabHost tabHost = (TabHost)activity.findViewById(R.id.tabhost2);
		tabHost.setCurrentTab(0);
		activity.refreshSaleListView();
		activity.refreshIntenvoryListView();
		Toast.makeText(activity, count + " items are added.", Toast.LENGTH_SHORT).show();
	}
}
