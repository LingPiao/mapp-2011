package com.mapp;

import android.app.Activity;
import android.os.Bundle;

public class SearchView extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TextView v = new EditText(this);
		// v.setText("search text");
		// setContentView(v);
		setContentView(R.layout.search);
	}

}
