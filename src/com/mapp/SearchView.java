package com.mapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class SearchView extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TextView v = new EditText(this);
		// v.setText("search text");
		// setContentView(v);
		setContentView(R.layout.search);
		Button submit = (Button) this.findViewById(R.id.button_search);
		final EditText searchText = (EditText) this.findViewById(R.id.edit_text_search);

		LayoutInflater inflater = LayoutInflater.from(this);
		final LinearLayout searchll = (LinearLayout) this.findViewById(R.id.listResult);
		final LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.favorite_list_view_item, null).findViewById(R.id.FavoriteListViewItemLayout);
		final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_view, null).findViewById(R.id.layout);
		final ListView lv = (ListView) layout.getChildAt(0);
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String st = searchText.getText().toString().trim();
				if (st.isEmpty()) {
					Toast.makeText(v.getContext(), R.string.search_text_required, Toast.LENGTH_SHORT).show();
					return;
				}
				SearchAdapter sa = new SearchAdapter(SearchView.this);
				sa.search(st);
				lv.setAdapter(sa);
				ll.removeAllViews();
				ll.addView(layout);
				searchll.removeView(ll);
				searchll.addView(ll);
				searchll.setBackgroundColor(Color.WHITE);
			}
		});
	}
}
