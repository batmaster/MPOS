package com.bbaf.mpos.inventoryAndSale.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.sale.SaleLineItem;

public class SaleListViewAdapter extends BaseAdapter {
	
	private Activity activity;
	private ArrayList<SaleLineItem> lineList;

	public SaleListViewAdapter(Activity activity) {
		this.activity = activity;
		this.lineList = Register.getInstance().getSale().getAllList();
	}

	@Override
	public int getCount() {
		return lineList.size();
	}

	@Override
	public Object getItem(int position) {
		return lineList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return new SaleListRow(activity, lineList.get(position));
	}

	public void notifyDataSetChanged() {
		lineList = Register.getInstance().getSale().getAllList();
		super.notifyDataSetChanged();
	}
	
	
}
