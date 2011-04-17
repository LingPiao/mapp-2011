package com.mapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LastestListAdapter extends BaseAdapter {

	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

	private LayoutInflater inflater;

	public LastestListAdapter(Context contex) {
		inflater = LayoutInflater.from(contex);
		List<Phone> phones = XmlUtils.loadPhones(Constants.TOP10_URL);
		for (Phone phone : phones) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("phoneId", phone.getId());
			map.put("pname", phone.getPname());
			map.put("appraise", "评分:" + phone.getAppraise());
			map.put("description", phone.getDesc());
			map.put("price", Constants.CHINA_YUN + phone.getPrice());
			map.put("image", Constants.BASE_URL + phone.getImage());
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
			myHolder.tv04 = (TextView) convertView.findViewById(R.id.TextView04);
			myHolder.phoneId = (TextView) convertView.findViewById(R.id.phoneId);
			myHolder.iv = (ImageView) convertView.findViewById(R.id.ImageView01);
			myHolder.actionImage = (ImageView) convertView.findViewById(R.id.actionImage);
			convertView.setTag(myHolder);
		} else {
			myHolder = (FavoriteViewHolder) convertView.getTag();
		}
		myHolder.tv01.setText(list.get(position).get("pname").toString());
		myHolder.tv02.setText(list.get(position).get("appraise").toString());
		myHolder.tv03.setText(list.get(position).get("description").toString());
		myHolder.tv04.setText(list.get(position).get("price").toString());
		myHolder.phoneId.setText(list.get(position).get("phoneId").toString());
		myHolder.iv.setImageBitmap(HttpUtil.getBitMap(list.get(position).get("image").toString()));
		myHolder.actionImage.setImageResource(R.drawable.list_add);

		final int p = position;

		myHolder.actionImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				@SuppressWarnings("unchecked")
				Map<String, Object> selected = (HashMap<String, Object>) LastestListAdapter.this.getItem(p);
				Phone phone = PhoneCache.getInstance().getPhone(selected.get("phoneId").toString());
				phone.setWatchedDate(new Date(System.currentTimeMillis()));
				MyFavoriteHolder.getInstance().addFavorite(phone);
				XmlUtils.saveFavorites(LastestListAdapter.this.inflater.getContext().getFilesDir().getAbsolutePath());
				// FavoriteListAdapter.this.notifyDataSetChanged();
				Toast.makeText(v.getContext(), " Add " + p + " ,id=" + phone.getId() + " to my Favorite Tab.", Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;
	}

}
