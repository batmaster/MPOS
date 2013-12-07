package com.bbaf.mpos.inventory.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.sale.SaleLineItem;
import com.bbaf.mpos.sale.payment.ui.SaleEditActivity;

class SaleListRow extends RemovableListRow {
	
	private SaleLineItem line;
	
	private CheckBox checkBoxRowSale;
	private TextView textViewRowIdSale;
	private TextView textViewRowNameSale;
	private TextView textViewRowUnitPriceSale;
	private TextView textViewRowQuantitySale;
	private ImageView imageViewEditRowSale;
	
	private boolean unitPriceChanged = false;
	
	private static final int EDIT_UNITPRICE_ACTIVITY_REQUESTCODE = 3;
	
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
		textViewRowUnitPriceSale.setText(String.format("%.2f", line.getUnitPrice()));
		textViewRowUnitPriceSale.setTextColor(line.getUnitPrice() == Register.getInstance().getInventory().getProduct(line.getProductDescription().getId()).getPrice() ? Color.GRAY : Color.BLUE);
		
		textViewRowQuantitySale = (TextView)findViewById(R.id.textViewRowDateSaleLedger);
		textViewRowQuantitySale.setText(String.valueOf(line.getQuantity()));
		textViewRowQuantitySale.setTextColor(Color.BLACK);
		
		imageViewEditRowSale = (ImageView)findViewById(R.id.imageViewEditRowSale);
		imageViewEditRowSale.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent editActivity = new Intent(activity.getApplicationContext(), SaleEditActivity.class);
				
				editActivity.putExtra("ProductDescription", getProduct());

				activity.startActivityForResult(editActivity, EDIT_UNITPRICE_ACTIVITY_REQUESTCODE);
			}
		});
	}
	
	@Override
	public boolean isChecked() {
		return checkBoxRowSale.isChecked();
	}
	
	@Override
	public ProductDescription getProduct() {
		return line.getProductDescription();
	}

	@Override
	public void remove() {
		Register.getInstance().getSale().removeSaleLineItem(line.getProductDescription());
	}
}
