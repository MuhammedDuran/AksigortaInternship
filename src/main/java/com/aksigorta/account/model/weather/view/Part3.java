package com.aksigorta.account.model.weather.view;

import java.time.LocalTime;

public class Part3 {
	private int temp;
	private int temp_min;
	private int temp_max;
	private String icon;
	private String url;
	private String date;
	private LocalTime time;
	private String day;


	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public int getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(int temp_min) {
		this.temp_min = temp_min;
	}

	public int getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(int temp_max) {
		this.temp_max = temp_max;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	

}
