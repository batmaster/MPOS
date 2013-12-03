package com.bbaf.mpos.inventory.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.FacadeController.Store;
import com.bbaf.mpos.ProductDescription.ProductDescription;

class InventoryListRow extends RemovableListRow {
	
	private ProductDescription product;
	
	private CheckBox checkBoxRowInventory;
	private TextView textViewRowIdInventory;
	private TextView textViewRowNameInventory;
	private TextView textViewRowQuantityInventory;
	private ImageView imageViewEditRowInventory;
	
	private static final int EDIT_ACTIVITY_REQUESTCODE = 1;
	
	public InventoryListRow(final Activity activity, ProductDescription product) {
		super(activity.getApplicationContext());
		LayoutInflater inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.inventory_list_row, this, true);
        
        this.product = product;
		
		checkBoxRowInventory = (CheckBox)findViewById(R.id.checkBoxRowInventory);
		
		textViewRowIdInventory = (TextView)findViewById(R.id.textViewRowIdInventory);
		textViewRowIdInventory.setText(product.getId());
		textViewRowIdInventory.setTextColor(Color.GRAY);
		
		textViewRowNameInventory = (TextView)findViewById(R.id.textViewRowNameInventory);
		textViewRowNameInventory.setText(product.getName());
		textViewRowNameInventory.setTextColor(Color.BLACK);
		
		textViewRowQuantityInventory = (TextView)findViewById(R.id.textViewRowQuantityInventory);
		int quantity = Register.getInstance().getInventory().getQuantity(product.getId());
		textViewRowQuantityInventory.setText(String.valueOf(quantity));
		textViewRowQuantityInventory.setTextColor(quantity < 0 ? Color.RED : Color.BLACK);
		
		imageViewEditRowInventory = (ImageView)findViewById(R.id.imageViewEditRowInventory);
		imageViewEditRowInventory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent editActivity = new Intent(activity.getApplicationContext(), EditProductActivity.class);
				
				editActivity.putExtra("ProductDescription", getProduct());
				int quantity = Register.getInstance().getInventory().getQuantity(getProduct().getId());
				editActivity.putExtra("ProductQuantity", quantity);

				activity.startActivityForResult(editActivity, EDIT_ACTIVITY_REQUESTCODE);
			}
		});
	}
	
	@Override
	public boolean isChecked() {
		return checkBoxRowInventory.isChecked();
	}
	
	@Override
	public ProductDescription getProduct() {
		return product;
	}

	@Override
	public void remove() {
		Register.getInstance().getInventory().removeProduct(product);
	}
}
