package com.aksigorta.account.model;

public class Weather {

	private String Sehir;
	private String HavaDurumu;
	private int Sicaklik;

	public String getSehir() {
		return Sehir;
	}

	public void setSehir(String sehir) {
		Sehir = sehir;
	}

	public String getHavaDurumu() {
		return HavaDurumu;
	}

	public void setHavaDurumu(String havaDurumu) {
		HavaDurumu = havaDurumu;
	}

	public int getSicaklik() {
		return Sicaklik;
	}

	public void setSicaklik(int sicaklik) {
		Sicaklik = sicaklik;
	}

}
