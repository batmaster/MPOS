package com.bbaf.mpos.saleledger.ui;

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
import android.widget.Toast;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.sale.Sale;
import com.bbaf.mpos.sale.SaleLineItem;
import com.bbaf.mpos.saleledger.ProductLedger;
import com.bbaf.mpos.saleledger.SaleLedger;

class ProductLedgerListRow extends RelativeLayout {
	
	private ProductLedger product;
	
	private TextView textViewRowIdProductLedger;
	private TextView textViewRowNameProductLedger;
	private TextView textViewRowTotalProductLedger;
	private TextView textViewRowQuantityProductLedger;
	private TextView textViewRowUnitPriceProductLedger;
	
	public ProductLedgerListRow(final Activity activity, ProductLedger product) {
		super(activity.getApplicationContext());
		LayoutInflater inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.product_ledger_list_row, this, true);
        
        this.product = product;
		
        textViewRowIdProductLedger = (TextView)findViewById(R.id.textViewRowIdProductLedger);
        textViewRowIdProductLedger.setText(product.getId());
        textViewRowIdProductLedger.setTextColor(Color.GRAY);
		
        textViewRowNameProductLedger = (TextView)findViewById(R.id.textViewRowNameProductLedger);
        textViewRowNameProductLedger.setText(product.getName());
        textViewRowNameProductLedger.setTextColor(Color.BLACK);
        
        textViewRowTotalProductLedger = (TextView)findViewById(R.id.textViewRowTotalProductLedger);
        textViewRowTotalProductLedger.setText(String.format("%.2f", product.getQuantity() * product.getUnitPrice()));
        textViewRowTotalProductLedger.setTextColor(Color.BLACK);

        textViewRowQuantityProductLedger = (TextView)findViewById(R.id.textViewRowQuantityProductLedger);
        textViewRowQuantityProductLedger.setText(String.format("x %d Pcs.", product.getQuantity()));
        textViewRowQuantityProductLedger.setTextColor(Color.GRAY);
        
        textViewRowUnitPriceProductLedger = (TextView)findViewById(R.id.textViewRowUnitPriceProductLedger);
        textViewRowUnitPriceProductLedger.setText(String.format("%.2f", product.getUnitPrice()));
        textViewRowUnitPriceProductLedger.setTextColor(Color.GRAY);
	}
	
	public ProductLedger getProduct() {
		return product;
	}
}
