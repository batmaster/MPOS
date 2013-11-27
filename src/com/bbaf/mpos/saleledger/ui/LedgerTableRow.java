package com.bbaf.mpos.saleledger.ui;

import java.util.Date;

import com.bbaf.mpos.ProductQuantity;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.sale.Sale;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;

public class LedgerTableRow extends TableRow {

	private Sale sale;
	private TextView rowDate;
	private TextView rowTatal;

	public LedgerTableRow(Context context,
			Sale sale) {
		super(context);

		this.sale = sale;

		rowDate = new TextView(context);
		Date date = sale.getDate();
		rowDate.setText(String.format("%d %d %d", date.getYear()+1900 , date.getMonth()+1 , date.getDate()));
		addView(rowDate);

		rowTatal = new TextView(context);
		rowTatal.setText(String.valueOf(sale.getTotal()));
		addView(rowTatal);
	}


	public Sale getSale() {
		return sale;
	}

}
