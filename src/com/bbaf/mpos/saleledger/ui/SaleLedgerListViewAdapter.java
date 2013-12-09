package com.bbaf.mpos.saleledger.ui;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.sale.Sale;
import com.bbaf.mpos.sale.SaleLineItem;
import com.bbaf.mpos.saleledger.SaleLedger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A class adapt ArrayList<SaleLedger> to be ListView shown in Sale view.
 */
public class SaleLedgerListViewAdapter extends BaseAdapter {
	
	private Activity activity;
	private ArrayList<SaleLedger> saleList;

	/**
	 * Constructor, using calling activity.
	 * @param activity calling activity
	 * @param saleList list of SaleLedger
	 */
	public SaleLedgerListViewAdapter(Activity activity, ArrayList<SaleLedger> saleList) {
		this.activity = activity;
		this.saleList = saleList;
	}

	@Override
	public int getCount() {
		return saleList.size();
	}

	@Override
	public Object getItem(int position) {
		return saleList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return new SaleLedgerListRow(activity, saleList.get(position));
	}

	/**
	 * Notify ListView to show new row lists by using list of SaleLedger lists
	 * @param saleList list of SaleLedger
	 */
	public void notifyDataSetChanged(ArrayList<SaleLedger> saleList) {
		this.saleList = saleList;
		super.notifyDataSetChanged();
	}
	
	/**
	 * Return total of Sale lists
	 * @return total of Sale lists
	 */
	public double getTotal() {
		double total = 0;
		for (int i = 0; i < saleList.size(); i++) {
			total += saleList.get(i).getTotal();
		}
		return total;
	}
}
