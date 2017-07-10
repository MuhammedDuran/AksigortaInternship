package com.aksigorta.account.web;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.aksigorta.account.model.weather.HavaDurumu;
import com.aksigorta.account.model.weather.HavaDurumuDetay;
import com.aksigorta.account.model.weather.view.Part1;
import com.aksigorta.account.model.weather.view.Part2;
import com.aksigorta.account.model.weather.view.Part3;
import com.aksigorta.account.webservis.JSONParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@SessionAttributes("user")
public class WeatherContoller {

	private String imageurl;

	private Logger log = LoggerFactory.getLogger(WeatherContoller.class);
	private String Humidity_text;
	private String Temp_text;
	private String Celcius;
	private String Saatlik_havadurumu_text;
	private String Gunluk_havadurumu_text;
	private String slash;
	private String date;
//	private int saat;
//	private int bolumsonuc;
//	private int cikarmasonuc;
//	private int max1, min1,max2,min2,max3,min3,max4,min4;
	private Map<DayOfWeek, String> dayOfWeekMap = new HashMap<>();
	
	{
		dayOfWeekMap.put(DayOfWeek.MONDAY, "Pazartesi");
		dayOfWeekMap.put(DayOfWeek.TUESDAY, "Salı");
		dayOfWeekMap.put(DayOfWeek.WEDNESDAY, "Çarşamba");
		dayOfWeekMap.put(DayOfWeek.THURSDAY, "Perşembe");
		dayOfWeekMap.put(DayOfWeek.FRIDAY, "Cuma");
		dayOfWeekMap.put(DayOfWeek.SATURDAY, "Cumartesi");
		dayOfWeekMap.put(DayOfWeek.SUNDAY, "Pazar");
	}
	
	
	

	@RequestMapping(value = "/getCityTemp", method = RequestMethod.GET)
	public String goToService(@RequestParam Map<String, String> params, Model model) {
		String sehir = params.get("sehir");
		String weatherUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" + sehir
				+ ",tr&units=metric&appid=1004f69aa9506063496f7891597238be&lang=tr";

		try {
			String json = JSONParser.getJSONFromUrl(weatherUrl);

			Gson gson = new GsonBuilder().create();
			HavaDurumu havaDurumu = gson.fromJson(json, HavaDurumu.class);
			model.addAttribute("part1", createCurrent(havaDurumu));
			model.addAttribute("Celcius", Celcius);
			model.addAttribute("Saatlik_havadurumu_text", Saatlik_havadurumu_text);
			model.addAttribute("slash",slash);
			model.addAttribute("Gunluk_havadurumu_text", Gunluk_havadurumu_text);
			model.addAttribute("humidity_text", Humidity_text);
			model.addAttribute("Temp_text", Temp_text);
			model.addAttribute("part2List", createHourly(havaDurumu));
			model.addAttribute("part3List", createDaily(havaDurumu));
			model.addAttribute("imageurl", imageurl);
//			model.addAttribute("max1", max1);
//			model.addAttribute("min1", min1);
//			model.addAttribute("max2", max2);
//			model.addAttribute("min2", min2);
//			model.addAttribute("max3", max3);
//			model.addAttribute("min3", min3);
//			model.addAttribute("max4", max4);
//			model.addAttribute("min4", min4);

		} catch (Exception e) {
			log.error("Rest Error", e);
		}

		return "welcome";
	}

	private Part1 createCurrent(HavaDurumu havaDurumu) {
		Part1 part = new Part1();
		part.setCity(havaDurumu.getCity().getName());
		HavaDurumuDetay havaDurumuDetay = havaDurumu.getList().get(0);
		part.setWeatherDescription(havaDurumuDetay.getWeather().get(0).getDescription());
		part.setTemp((int) havaDurumuDetay.getMain().getTemp());

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM HH:mm", new Locale("tr"));
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", new Locale("tr"));
		date = dateFormat.format(new Date());
		part.setDate(date);

		date = dateFormat2.format(new Date());

		part.setHumidity(havaDurumuDetay.getMain().getHumidity());
		part.setIcon(havaDurumuDetay.getWeather().get(0).getIcon());
		Humidity_text = "Nem : %" + part.getTemp();
		Temp_text = part.getTemp() + "°C";
		Celcius = "°C";

		imageurl = "http://openweathermap.org/img/w/" + part.getIcon() + ".png";
		
		Gunluk_havadurumu_text = "Günlük Hava Durumu";	
		Saatlik_havadurumu_text = "Saatlik Hava Durumu";
		slash = "/";

		return part;
	}

	private List<Part2> createHourly(HavaDurumu havaDurumu) {

		List<Part2> part2List = new ArrayList<Part2>();

		for (int i = 1; i < 7; i++) {
			Part2 part = new Part2();
			HavaDurumuDetay havaDurumuDetay = havaDurumu.getList().get(i);
			part.setTemp((int) havaDurumuDetay.getMain().getTemp());
			part.setIcon(havaDurumuDetay.getWeather().get(0).getIcon());
			part.setUrl("http://openweathermap.org/img/w/" + part.getIcon() + ".png");

			String dateTimeString = havaDurumu.getList().get(i).getDt_txt();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

			part.setTime(dateTime.toLocalTime());
			part2List.add(part);

		}

	
		return part2List;

	}
	
	private List<Part3> createDaily(HavaDurumu havaDurumu) 
	{
		Map<LocalDate, List<HavaDurumuDetay>> gunlereGoreHavaDurumuDetayMap = gunlereGoreHavaDurumuDetayMapOlustur(havaDurumu);
		bugununVerisiniCikar(gunlereGoreHavaDurumuDetayMap);
		List<Part3> part3Listesi = part3ListesiOlustur(gunlereGoreHavaDurumuDetayMap);
		return part3Listesi;
	}

//	private void minMaxAyarla(Map<LocalDate, List<HavaDurumuDetay>> gunlereGoreHavaDurumuDetayMap) {
//		LocalDate day1 = LocalDate.now().plusDays(1);
//		List<HavaDurumuDetay> day1list = gunlereGoreHavaDurumuDetayMap.get(day1);
//		min1 = minimumSicaklikBul(day1list);
//		max1 = maximumSicaklikBul(day1list);
//		
//		LocalDate day2 = LocalDate.now().plusDays(2);
//		List<HavaDurumuDetay> day2list = gunlereGoreHavaDurumuDetayMap.get(day2);
//		min2 = minimumSicaklikBul(day2list);
//		max2 = maximumSicaklikBul(day2list);
//		
//		LocalDate day3 = LocalDate.now().plusDays(3);
//		List<HavaDurumuDetay> day3list = gunlereGoreHavaDurumuDetayMap.get(day3);
//		min3 = minimumSicaklikBul(day3list);
//		max3 = maximumSicaklikBul(day3list);
//		
//		LocalDate day4 = LocalDate.now().plusDays(4);
//		List<HavaDurumuDetay> day4list = gunlereGoreHavaDurumuDetayMap.get(day4);
//		min4 = minimumSicaklikBul(day4list);
//		max4 = maximumSicaklikBul(day4list);
//	}
	
	private int minimumSicaklikBul(List<HavaDurumuDetay> havaDurumuDetayList)
	{
		int min = Integer.MAX_VALUE;
		for(HavaDurumuDetay havaDurumuDetay: havaDurumuDetayList)
		{
			if((int) havaDurumuDetay.getMain().getTemp() < min)
			{
				min = (int) havaDurumuDetay.getMain().getTemp();
			}
		}
		return min;
	}
	
	private int maximumSicaklikBul(List<HavaDurumuDetay> havaDurumuDetayList)
	{
		int max = Integer.MIN_VALUE;
		for(HavaDurumuDetay havaDurumuDetay: havaDurumuDetayList)
		{
			if((int) havaDurumuDetay.getMain().getTemp() > max)
			{
				max = (int) havaDurumuDetay.getMain().getTemp();
			}
		}
		return max;
	}

	private List<Part3> part3ListesiOlustur(Map<LocalDate, List<HavaDurumuDetay>> gunlereGoreHavaDurumuDetayMap) 
	{
		List<Part3> part3List = new ArrayList<>();
		for(Entry<LocalDate, List<HavaDurumuDetay>> entrySet: gunlereGoreHavaDurumuDetayMap.entrySet())
		{
			List<HavaDurumuDetay> havaDurumuDetayList = entrySet.getValue();
			String icon = havaDurumuDetayList.get(4).getWeather().get(0).getIcon();
			Part3 part3 = new Part3();
			part3.setIcon(icon);
			part3.setUrl("http://openweathermap.org/img/w/" + icon + ".png");
			part3.setTemp_max(maximumSicaklikBul(havaDurumuDetayList));
			part3.setTemp_min(minimumSicaklikBul(havaDurumuDetayList));
			part3.setDay(dayOfWeekMap.get(entrySet.getKey().getDayOfWeek()));
			part3List.add(part3);
		}
		return part3List;
	}

	private void bugununVerisiniCikar(Map<LocalDate, List<HavaDurumuDetay>> gunlereGoreHavaDurumuDetayMapOlustur) 
	{
		gunlereGoreHavaDurumuDetayMapOlustur.remove(LocalDate.now());
	}

	private Map<LocalDate, List<HavaDurumuDetay>> gunlereGoreHavaDurumuDetayMapOlustur(HavaDurumu havaDurumu) 
	{
		Map<LocalDate, List<HavaDurumuDetay>> gunlereGoreHavaDurumuDetayMap = new LinkedHashMap<>();
		for(HavaDurumuDetay havaDurumuDetay : havaDurumu.getList())
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDate date = LocalDate.parse(havaDurumuDetay.getDt_txt(), formatter);
			if(!gunlereGoreHavaDurumuDetayMap.containsKey(date))
			{
				gunlereGoreHavaDurumuDetayMap.put(date, new ArrayList<>());
			}
			gunlereGoreHavaDurumuDetayMap.get(date).add(havaDurumuDetay);
		}
		return gunlereGoreHavaDurumuDetayMap;
	}

//	private List<Part3> createDaily2(HavaDurumu havaDurumu) {
//		List<Part3> part3List = new ArrayList<Part3>();
//		List<HavaDurumuDetay> havaDurumuDetay = havaDurumu.getList();
//		LocalTime time = LocalTime.now();
//		saat = time.getHour();
//		bolumsonuc = saat / 3;
//		cikarmasonuc = 8 - bolumsonuc;
//		for (int i = cikarmasonuc; i < cikarmasonuc + 8; i++) {
//			Part3 part3 = new Part3();
//			part3.setTemp((int) havaDurumu.getList().get(i).getMain().getTemp());
//			part3List.add(part3);
//		}
//		max1 = part3List.get(0).getTemp();
//		min1 = part3List.get(0).getTemp();
//
//		for (int j = 0; j < 8; j++) {
//			if (max1 <= part3List.get(j).getTemp()) {
//				max1 = part3List.get(j).getTemp();
//			}
//			if (min1 >= part3List.get(j).getTemp()) {
//				min1 = part3List.get(j).getTemp();
//			}
//		}
//		for (int i = cikarmasonuc+8; i < cikarmasonuc + 16; i++) {
//			Part3 part3 = new Part3();
//			part3.setTemp((int) havaDurumu.getList().get(i).getMain().getTemp());
//			part3List.add(part3);
//		}
//		max2 = part3List.get(8).getTemp();
//		min2 = part3List.get(8).getTemp();
//
//		for (int j = 8; j < 16; j++) {
//			if (max2 <= part3List.get(j).getTemp()) {
//				max2 = part3List.get(j).getTemp();
//			}
//			if (min2 >= part3List.get(j).getTemp()) {
//				min2 = part3List.get(j).getTemp();
//			}
//		}
//		for (int i = cikarmasonuc+16; i < cikarmasonuc + 24; i++) {
//			Part3 part3 = new Part3();
//			part3.setTemp((int) havaDurumu.getList().get(i).getMain().getTemp());
//			part3List.add(part3);
//		}
//		max3 = part3List.get(16).getTemp();
//		min3 = part3List.get(16).getTemp();
//
//		for (int j = 16; j < 24; j++) {
//			if (max3 <= part3List.get(j).getTemp()) {
//				max3 = part3List.get(j).getTemp();
//			}
//			if (min3 >= part3List.get(j).getTemp()) {
//				min3 = part3List.get(j).getTemp();
//			}
//		}
//		for (int i = cikarmasonuc+24; i < cikarmasonuc + 32; i++) {
//			Part3 part3 = new Part3();
//			part3.setTemp((int) havaDurumu.getList().get(i).getMain().getTemp());
//			part3List.add(part3);
//		}
//		max4 = part3List.get(24).getTemp();
//		min4 = part3List.get(24).getTemp();
//
//		for (int j = 24; j < 32; j++) {
//			if (max4 <= part3List.get(j).getTemp()) {
//				max4 = part3List.get(j).getTemp();
//			}
//			if (min4 >= part3List.get(j).getTemp()) {
//				min4 = part3List.get(j).getTemp();
//			}
//		}
//
//		for(int i = 4 ; i<=28 ; i=i+8){
//			Part3 part3 = new Part3();
//			part3.setIcon(havaDurumu.getList().get(cikarmasonuc+i).getWeather().get(0).getIcon());
//			part3.setUrl("http://openweathermap.org/img/w/" + part3.getIcon() + ".png");
//			part3List.add(part3);
//		}
//		
//		return part3List;
//	}
}
