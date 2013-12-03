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
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.sale.SaleLineItem;

class SaleListRow extends RelativeLayout {
	
	private SaleLineItem line;
	
	private CheckBox checkBoxRowSale;
	private TextView textViewRowIdSale;
	private TextView textViewRowNameSale;
	private TextView textViewRowUnitPriceSale;
	private TextView textViewRowQuantitySale;
	private ImageView imageViewEditRowSale;
	
	public SaleListRow(final Activity activity, SaleLineItem line) {
		super(activity.getApplicationContext());
		LayoutInflater inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sale_list_row, this, true);
        
        this.line = line;
		
		checkBoxRowSale = (CheckBox)findViewById(R.id.checkBoxRowSale);
		
		textViewRowIdSale = (TextView)findViewById(R.id.textViewRowIdSale);
		textViewRowIdSale.setText(line.getProductDescription().getId());
		textViewRowIdSale.setTextColor(Color.GRAY);
		
		textViewRowNameSale = (TextView)findViewById(R.id.textViewRowTotalSaleLedger);
		textViewRowNameSale.setText(line.getProductDescription().getName());
		textViewRowNameSale.setTextColor(Color.BLACK);
		
		textViewRowUnitPriceSale = (TextView)findViewById(R.id.textViewRowUnitPriceSale);
		textViewRowUnitPriceSale.setText(String.valueOf(line.getProductDescription().getPrice()));
		textViewRowUnitPriceSale.setTextColor(Color.GRAY);
		
		textViewRowQuantitySale = (TextView)findViewById(R.id.textViewRowDateSaleLedger);
		textViewRowQuantitySale.setText(String.valueOf(line.getQuantity()));
		textViewRowQuantitySale.setTextColor(Color.BLACK);
		
		imageViewEditRowSale = (ImageView)findViewById(R.id.imageViewEditRowSale);
		imageViewEditRowSale.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// edit prod in sale //
				/*Intent editActivity = new Intent(getContext(), EditProductActivity.class);
				getContext().startActivity(editActivity);*/
				
			}
		});
	}
	
	public boolean isChecked() {
		return checkBoxRowSale.isChecked();
	}
	
	public ProductDescription getProduct() {
		return line.getProductDescription();
	}
}
