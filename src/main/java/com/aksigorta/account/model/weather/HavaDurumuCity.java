package com.aksigorta.account.model.weather;

import java.util.List;

public class HavaDurumuCity {
	private int id;
	private String name;
	private HavaDurumuCoord coord;
	private String country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HavaDurumuCoord getCoord() {
		return coord;
	}

	public void setCoord(HavaDurumuCoord coord) {
		this.coord = coord;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
