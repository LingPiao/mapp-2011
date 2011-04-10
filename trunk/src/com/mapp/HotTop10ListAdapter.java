package com.mapp;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HotTop10ListAdapter extends BaseAdapter {

	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

	private LayoutInflater inflater;

	public HotTop10ListAdapter(Context contex) {
		inflater = LayoutInflater.from(contex);
		List<Phone> phones = loadPhones(Constants.TOP10_URL);
		for (Phone phone : phones) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", phone.getId());
			map.put("pname", phone.getPname());
			map.put("appraise", "评分:" + phone.getAppraise());
			map.put("description", phone.getDesc());
			map.put("price", Constants.CHINA_YUN + phone.getPrice());
			map.put("image", Constants.BASE_URL + phone.getImage());
			list.add(map);
		}
	}

	private List<Phone> loadPhones(String url) {
		String xml = HttpUtil.getXmlString(url);
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		XMLHandler handler = new XMLHandler();
		try {
			XMLReader reader = saxParserFactory.newSAXParser().getXMLReader();
			reader.setContentHandler(handler);
			reader.parse(new InputSource(new StringReader(xml)));
		} catch (Exception e) {
			Log.e(Constants.MODULE_NAME, e.getMessage());
		}

		return handler.getValues();
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
		myHolder.iv.setImageBitmap(HttpUtil.getBitMap(list.get(position).get("image").toString()));
		myHolder.actionImage.setImageResource(R.drawable.list_add);

		final int p = position;

		myHolder.actionImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), " Add " + p + " to my Favorite Tab.", Toast.LENGTH_SHORT).show();
				// list.remove(p);
				// TODO add to favorite xml file.
				// HotTop10ListAdapter.this.notifyDataSetChanged();
				// TabHost tabHost = getParent();
			}
		});

		return convertView;
	}

}
