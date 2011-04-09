package com.mapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LastestView extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView v = new TextView(this);
		v.setText("This the lastest tab.");
		setContentView(v);
	}

}
