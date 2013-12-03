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
import android.widget.Toast;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.sale.Sale;
import com.bbaf.mpos.sale.SaleLineItem;

class SaleLedgerListRow extends RelativeLayout {
	
	private Sale sale;
	
	private TextView textViewRowDateSaleLedger;
	private TextView textViewRowTotalSaleLedger;
	
	public SaleLedgerListRow(final Activity activity, Sale sale) {
		super(activity.getApplicationContext());
		LayoutInflater inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sale_ledger_list_row, this, true);
        
        this.sale = sale;
		
        textViewRowDateSaleLedger = (TextView)findViewById(R.id.textViewRowDateSaleLedger);
        textViewRowDateSaleLedger.setText(sale.getDate().toString());
        textViewRowDateSaleLedger.setTextColor(Color.BLACK);
		
        textViewRowTotalSaleLedger = (TextView)findViewById(R.id.textViewRowTotalSaleLedger);
        textViewRowTotalSaleLedger.setText(String.valueOf(sale.getTotal()));
        textViewRowTotalSaleLedger.setTextColor(Color.BLACK);
		
        setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(activity.getApplicationContext(), getSale().getAllList() + "", Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
	public Sale getSale() {
		return sale;
	}
}
