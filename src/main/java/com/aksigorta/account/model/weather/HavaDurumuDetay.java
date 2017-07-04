
package com.aksigorta.account.model.weather;

import java.util.List;

public class HavaDurumuDetay {
	private long dt;
	private HavaDurumuMain main;
	private List<HavaDurumuWeather> weather;
	private HavaDurumuClouds clouds;
	private HavaDurumuWind wind;
	private HavaDurumuSys sys;
	private String dt_txt;
	public long getDt() {
		return dt;
	}
	public void setDt(long dt) {
		this.dt = dt;
	}
	public HavaDurumuMain getMain() {
		return main;
	}
	public void setMain(HavaDurumuMain main) {
		this.main = main;
	}
	public List<HavaDurumuWeather> getWeather() {
		return weather;
	}
	public void setWeather(List<HavaDurumuWeather> weather) {
		this.weather = weather;
	}
	public HavaDurumuClouds getClouds() {
		return clouds;
	}
	public void setClouds(HavaDurumuClouds clouds) {
		this.clouds = clouds;
	}
	public HavaDurumuWind getWind() {
		return wind;
	}
	public void setWind(HavaDurumuWind wind) {
		this.wind = wind;
	}
	public HavaDurumuSys getSys() {
		return sys;
	}
	public void setSys(HavaDurumuSys sys) {
		this.sys = sys;
	}
	public String getDt_txt() {
		return dt_txt;
	}
	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}
	
}
