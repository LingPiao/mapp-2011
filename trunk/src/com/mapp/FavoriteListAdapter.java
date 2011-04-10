package com.mapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FavoriteListAdapter extends BaseAdapter {

	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

	private LayoutInflater inflater;

	public FavoriteListAdapter(Context contex) {
		inflater = LayoutInflater.from(contex);
		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("n01", "NOKIA E72");
			map.put("n02", "关注日:01-24 +200   评分:2.1");
			map.put("n03", "个人觉得系统比较稳定，待机时间比较长内存小了，运行速度慢了，不支持多点触摸.");
			list.add(map);
		}

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final FavoriteViewHolder myHolder;
		if (convertView == null) {
			myHolder = new FavoriteViewHolder();
			convertView = inflater.inflate(R.layout.favorite_list_view_item, null);
			myHolder.tv01 = (TextView) convertView.findViewById(R.id.TextView01);
			myHolder.tv02 = (TextView) convertView.findViewById(R.id.TextView02);
			myHolder.tv03 = (TextView) convertView.findViewById(R.id.TextView03);
			myHolder.iv = (ImageView) convertView.findViewById(R.id.ImageView01);
			myHolder.actionImage = (ImageView) convertView.findViewById(R.id.actionImage);
			convertView.setTag(myHolder);
		} else {
			myHolder = (FavoriteViewHolder) convertView.getTag();
		}
		// myHolder.iv.setImageBitmap(returnBitMap("http://www.eoeandroid.com/z/data/avatar/000/11/81/51_avatar_middle.jpg"));
		myHolder.tv01.setText(list.get(position).get("n01").toString());
		myHolder.tv02.setText(list.get(position).get("n02").toString());
		myHolder.tv03.setText(list.get(position).get("n03").toString());
		// http://2b.zol-img.com.cn/product/45_280x210/589/ceey73Fphwmw6.jpg
		// myHolder.actionImage.setImageBitmap(returnBitMap("http://10.0.2.2:8080/images/c601.png"));
		final int p = position;
		myHolder.actionImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "Position=" + p, Toast.LENGTH_SHORT).show();
				list.remove(p);
				FavoriteListAdapter.this.notifyDataSetChanged();
			}
		});
		return convertView;
	}

	public void add(Phone phone) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("n01", "newPhone");
		map.put("n02", "关注日:01-24 +200   评分:2.1");
		map.put("n03", "个人觉得系统比较稳定，待机时间比较长内存小了，运行速度慢了，不支持多点触摸.");
		list.add(map);
		FavoriteListAdapter.this.notifyDataSetChanged();

	}

}
