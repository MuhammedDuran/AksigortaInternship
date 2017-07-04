package com.aksigorta.account.model.weather;

import java.util.List;

public class HavaDurumu {
	private String cod;
	private float message;
	private int cnt;
	private List<HavaDurumuDetay> list;
	private HavaDurumuCity city;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public float getMessage() {
		return message;
	}

	public void setMessage(float message) {
		this.message = message;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public List<HavaDurumuDetay> getList() {
		return list;
	}

	public void setList(List<HavaDurumuDetay> list) {
		this.list = list;
	}

	public HavaDurumuCity getCity() {
		return city;
	}

	public void setCity(HavaDurumuCity city) {
		this.city = city;
	}
}
