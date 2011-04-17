package com.mapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFavoriteHolder {

	private Map<String, Phone> favoriteList = new HashMap<String, Phone>();

	private static MyFavoriteHolder instance = null;

	private MyFavoriteHolder() {
	}

	public static MyFavoriteHolder getInstance() {
		if (instance == null) {
			instance = new MyFavoriteHolder();
		}
		return instance;
	}

	public void addFavorite(Phone phone) {
		this.favoriteList.put(phone.getId(), phone);
	}

	public void remove(String id) {
		this.favoriteList.remove(id);
	}

	public boolean hasItems() {
		return this.favoriteList.size() > 0;
	}

	public List<Phone> getFavorites() {
		List<Phone> list = new ArrayList<Phone>();
		for (String id : favoriteList.keySet()) {
			list.add(favoriteList.get(id));
		}
		return list;
	}

}
