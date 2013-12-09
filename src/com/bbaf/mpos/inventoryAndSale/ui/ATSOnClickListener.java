package com.bbaf.mpos.inventoryAndSale.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.ProductDescription.ProductDescription;
/**
 * ActionListener of AddToSale button in Sale view.
 */
public class ATSOnClickListener implements OnClickListener {

	private ListView listViewInventory;
	private InventoryandSaleActivity activity;

	/**
	 * Constructor, use calling activity as a context.
	 * @param listViewInventory ListView of items in Inventory
	 * @param activity calling activity
	 */
	public ATSOnClickListener(ListView listViewInventory, InventoryandSaleActivity activity) {
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