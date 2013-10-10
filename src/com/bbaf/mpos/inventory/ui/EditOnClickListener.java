package com.bbaf.mpos.inventory.ui;

import com.bbaf.mpos.ProductDescription;
import com.bbaf.mpos.ProductQuantity;
import com.bbaf.mpos.inventory.InventoryDBHelper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;

public class EditOnClickListener implements OnClickListener {

	private TableLayout tableLayout;
	private InventoryDBHelper dbDAO;
	private Activity activity;
	
	public EditOnClickListener(TableLayout tableLayout,InventoryDBHelper dbDAO,Activity activity) {
		this.tableLayout = tableLayout;
		this.dbDAO = dbDAO;
		this.activity = activity;
	}
	
	@Override
	public void onClick(View v) {
		// now, must edit every item that is checked
		for (int i = 1; i < tableLayout.getChildCount(); i++) {
			try {
				InventoryTableRow row = (InventoryTableRow) tableLayout
						.getChildAt(i);
				// can you break until first finish??
				if (row.isChecked()) {
					Intent editActivity = new Intent(
							activity.getApplicationContext(),
							EditProductActivity.class);
					ProductDescription product = row.getProduct();
					editActivity
							.putExtra("ProductDescription", product);
					ProductQuantity quantity = dbDAO
							.getQuantity(product.getId());
					editActivity.putExtra("ProductQuantity", quantity);

					// EDIT_ACTIVITY_REQUESTCODE = 1
					activity.startActivityForResult(editActivity,
							-1);
				}

			} catch (ClassCastException e) {
				// prevent casting TableHead
			}
		}
	}
	
}
