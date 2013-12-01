package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.FacadeController.Store;
import com.bbaf.mpos.ProductDescription.ProductDescription;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.Toast;

public class ATSOnClickListener implements OnClickListener {

	private TableLayout tableLayout;
	private InventoryActivity2 activity;

	public ATSOnClickListener(TableLayout tableLayout, InventoryActivity2 activity) {
		this.tableLayout = tableLayout;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		// bat: now, must edit every item that is checked
		int count = 0;
		for (int i = 1; i < tableLayout.getChildCount(); i++) {
			try {
				InventoryTableRow row = (InventoryTableRow) tableLayout
						.getChildAt(i);
				// bat: can you break until first finish??, i try to use
				// startActivityForResult() instead startActivity() but it still don't break
				if (row.isChecked()) {
					
					ProductDescription product = row.getProduct();
					Register.getInstance().addItem(product, 1);
					count++;
				}

			} catch (ClassCastException e) {
				// bat: prevent casting TableHead
			}
		}
		TabHost tabHost = (TabHost)activity.findViewById(R.id.tabhost2);
		tabHost.setCurrentTab(0);
		activity.refreshSaleTable();
		activity.refreshIntenvoryTable();
		Toast.makeText(activity, count + " items are added.", Toast.LENGTH_SHORT).show();
	}

}
