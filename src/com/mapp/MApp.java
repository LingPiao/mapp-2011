package com.mapp;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;

public class MApp extends TabActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TextView tv = new TextView(this);
		// tv.setText("Hello, Android!");
		// tv.setTextColor(android.graphics.Color.RED);
		// setContentView(tv);

		setContentView(R.layout.main);
		TabHost tabHost = getTabHost();
		// tabHost.setup(this.getLocalActivityManager());
		// LayoutInflater.from(this).inflate(R.layout.main,tabHost.getTabContentView(),
		// true);

		tabHost.addTab(tabHost.newTabSpec("TAB_MY_FAVORITE").setIndicator(getResources().getString(R.string.tab1_title))
				.setContent(new Intent().setClass(this, MyFavoriteView.class)));
		tabHost.addTab(tabHost.newTabSpec("TAB_HOT_TOP_10").setIndicator(getResources().getString(R.string.tab2_title))
				.setContent(new Intent().setClass(this, HotTop10View.class)));
		// tabHost.addTab(tabHost.newTabSpec("TAB_LASTEST").setIndicator(getResources().getString(R.string.tab3_title)).setContent(R.id.tabView3));
		tabHost.addTab(tabHost.newTabSpec("TAB_LASTEST").setIndicator(getResources().getString(R.string.tab3_title))
				.setContent(new Intent().setClass(this, LastestView.class)));

		tabHost.addTab(tabHost.newTabSpec("TAB_SEARCH").setIndicator(getResources().getString(R.string.tab4_title))
				.setContent(new Intent().setClass(this, SearchView.class)));

		tabHost.setBackgroundColor(Color.argb(150, 109, 115, 131));
		// tabHost.setBackgroundResource(R.drawable.icon);

		XmlUtils.loadMyfavorites(MApp.this.getCurrentActivity().getApplicationContext().getFilesDir().getAbsolutePath() + "/" + Constants.FAVORITES_FILE);

		if (MyFavoriteHolder.getInstance().hasItems()) {
			tabHost.setCurrentTab(0);
		} else {
			tabHost.setCurrentTab(1);
		}

	}
}