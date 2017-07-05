package com.aksigorta.account.web;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

	@RequestMapping(value = "/getCityTemp", method = RequestMethod.GET)
	public String goToService(@RequestParam Map<String, String> params, Model model) {
		String sehir = params.get("sehir");
		String weatherUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" + sehir
				+ ",tr&units=metric&appid=1004f69aa9506063496f7891597238be";

		try {
			String json = JSONParser.getJSONFromUrl(weatherUrl);

			Gson gson = new GsonBuilder().create();
			HavaDurumu havaDurumu = gson.fromJson(json, HavaDurumu.class);
			model.addAttribute("part1", createCurrent(havaDurumu));
			model.addAttribute("Celcius",Celcius);
			model.addAttribute("humidity_text",Humidity_text);
			model.addAttribute("Temp_text",Temp_text);
			model.addAttribute("part2List", createHourly(havaDurumu));
			model.addAttribute("imageurl", imageurl);

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
		String date = dateFormat.format(new Date());
		part.setDate(date);

		part.setHumidity(havaDurumuDetay.getMain().getHumidity());
		part.setIcon(havaDurumuDetay.getWeather().get(0).getIcon());
		Humidity_text = "Nem : %" +  part.getTemp();
		Temp_text = part.getTemp() + " °C";
		Celcius = " °C";

		imageurl = "http://openweathermap.org/img/w/" + part.getIcon() + ".png";

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

	public static void main(String[] args) {
		Date d = new Date(1487257200);
		System.out.println("Date :" + d);
	}

}
