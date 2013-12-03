package com.bbaf.mpos.inventory.description.ui;

import com.bbaf.mpos.R;
import com.bbaf.mpos.R.layout;
import com.bbaf.mpos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
/**
 * @author Sarathit Sangtaweep 5510546182
 */
public class DescriptionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.description, menu);
		return true;
	}

}
