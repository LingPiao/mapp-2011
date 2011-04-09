package com.mapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;

public class HotTop10View extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TextView v = new TextView(this);
		// v.setText("This the HotTop10 tab.");
		// setContentView(v);

		setContentView(R.layout.favorite_list_view_item);

		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout ll = (LinearLayout) findViewById(R.id.FavoriteListViewItemLayout);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_view, null).findViewById(R.id.layout);
		ListView lv = (ListView) layout.getChildAt(0);
		lv.setAdapter(new HotTop10ListAdapter(HotTop10View.this));
		ll.removeAllViews();
		ll.addView(layout);
	}

}
