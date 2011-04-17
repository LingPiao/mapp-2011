package com.mapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MyFavoriteView extends Activity {

	LinearLayout ll;
	LinearLayout layout;
	ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite_list_view_item);

		LayoutInflater inflater = LayoutInflater.from(this);

		ll = (LinearLayout) findViewById(R.id.FavoriteListViewItemLayout);
		layout = (LinearLayout) inflater.inflate(R.layout.list_view, null).findViewById(R.id.layout);
		lv = (ListView) layout.getChildAt(0);
		lv.setAdapter(new FavoriteListAdapter(MyFavoriteView.this));
		ll.removeAllViews();
		ll.addView(layout);

	}

	@Override
	public void onResume() {
		super.onResume();
		lv.setAdapter(new FavoriteListAdapter(MyFavoriteView.this));
		ll.removeAllViews();
		ll.addView(layout);
	}

}
