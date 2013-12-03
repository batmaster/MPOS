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
import com.bbaf.mpos.saleledger.SaleLedger;

class SaleLedgerListRow extends RelativeLayout {
	
	private SaleLedger sale;
	
	private TextView textViewRowDateSaleLedger;
	private TextView textViewRowTimeSaleLedger;
	private TextView textViewRowTotalSaleLedger;
	
	public SaleLedgerListRow(final Activity activity, final SaleLedger sale) {
		super(activity.getApplicationContext());
		LayoutInflater inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sale_ledger_list_row, this, true);
        
        this.sale = sale;
		
        textViewRowDateSaleLedger = (TextView)findViewById(R.id.textViewRowDateSaleLedger);
        textViewRowDateSaleLedger.setText(sale.getDateX());
        textViewRowDateSaleLedger.setTextColor(Color.BLACK);
        
        textViewRowTimeSaleLedger = (TextView)findViewById(R.id.textViewRowTimeSaleLedger);
        textViewRowTimeSaleLedger.setText(sale.getTimeX());
        textViewRowTimeSaleLedger.setTextColor(Color.BLACK);
		
        textViewRowTotalSaleLedger = (TextView)findViewById(R.id.textViewRowTotalSaleLedger);
        textViewRowTotalSaleLedger.setText(String.format("%.2f", sale.getTotal()));
        textViewRowTotalSaleLedger.setTextColor(Color.BLACK);
		
        setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent productListActivity = new Intent(activity, SaleLedgerProductListActivity.class);
				productListActivity.putExtra("SaleLedger", sale);
				productListActivity.putExtra("ProductLedgerList", sale.getAllProductLedger());
				activity.startActivity(productListActivity);
			}
		});
	}
	
	public SaleLedger getSale() {
		return sale;
	}
}
