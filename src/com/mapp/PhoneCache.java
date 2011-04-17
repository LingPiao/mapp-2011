package com.mapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneCache {
	private Map<String, Phone> cache = new HashMap<String, Phone>();

	private static PhoneCache instance = null;

	private PhoneCache() {
	}

	public static PhoneCache getInstance() {
		if (instance == null) {
			instance = new PhoneCache();
		}
		return instance;
	}

	public void addFavorite(Phone phone) {
		this.cache.put(phone.getId(), phone);
	}

	public void addFavorite(List<Phone> phones) {
		for (Phone phone : phones) {
			this.cache.put(phone.getId(), phone);
		}
	}

	public List<Phone> getPhones() {
		List<Phone> list = new ArrayList<Phone>();
		for (String id : cache.keySet()) {
			list.add(cache.get(id));
		}
		return list;
	}

	public Phone getPhone(String id) {
		return cache.get(id);
	}
}
