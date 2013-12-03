package com.bbaf.mpos.inventory.ui;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.ProductDescription.ProductDescription;
import com.bbaf.mpos.sale.SaleLineItem;

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
