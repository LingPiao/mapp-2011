package com.mapp;

import java.util.Date;

public class Phone {
	private String id;
	private String pname;
	private String price;
	private String trend;
	private String appraise;
	private String image;
	private String desc;
	private Date watchedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTrend() {
		return trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}

	public String getAppraise() {
		return appraise;
	}

	public void setAppraise(String appraise) {
		this.appraise = appraise;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getWatchedDate() {
		return watchedDate;
	}

	public void setWatchedDate(Date watchedDate) {
		this.watchedDate = watchedDate;
	}

}
