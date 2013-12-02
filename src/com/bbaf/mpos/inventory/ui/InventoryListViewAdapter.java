package com.bbaf.mpos.inventory.ui;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.bbaf.mpos.R;
import com.bbaf.mpos.FacadeController.Register;
import com.bbaf.mpos.ProductDescription.ProductDescription;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class InventoryListViewAdapter extends BaseAdapter {
	
	private Activity activity;
	private ArrayList<ProductDescription> productList;
	
	private static LayoutInflater inflater = null;

	public InventoryListViewAdapter(Activity activity, ArrayList<ProductDescription> productList) {
		this.activity = activity;
		this.productList = productList;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return productList.size();
	}

	@Override
	public Object getItem(int position) {
		return productList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (convertView == null)
			view = inflater.inflate(R.layout.inventory_list_row, null);
		
		CheckBox checkBoxRow = (CheckBox)activity.findViewById(R.id.checkBoxRow);
		
		TextView textViewRowId = (TextView)view.findViewById(R.id.textViewRowId);
		textViewRowId.setText(productList.get(position).getId());
		textViewRowId.setTextColor(Color.GRAY);
		
		TextView textViewRowName = (TextView)view.findViewById(R.id.textViewRowName);
		textViewRowName.setText(productList.get(position).getName());
		
		TextView textViewRowQuantity = (TextView)view.findViewById(R.id.textViewRowQuantity);
		int quantity = Register.getInstance().getInventory().getQuantity(productList.get(position).getId());
		textViewRowQuantity.setText(String.valueOf(quantity));
		textViewRowQuantity.setTextColor(quantity < 0 ? Color.RED : Color.BLACK);
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(activity.getApplicationContext(), "Hi " + productList.get(position).getName(), Toast.LENGTH_SHORT).show();
				
			}
		});
		
		return view;
	}

}
