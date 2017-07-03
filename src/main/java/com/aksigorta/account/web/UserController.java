package com.aksigorta.account.web;

import java.util.Date;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.aksigorta.account.model.User;
import com.aksigorta.account.service.SecurityService;
import com.aksigorta.account.service.UserService;
import com.aksigorta.account.validator.UserValidator;
import com.aksigorta.account.webservis.JSONParser;
import com.mysql.fabric.xmlrpc.base.Data;

@Controller
@SessionAttributes("user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	private int tempNo;
	private int humidity;

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/welcome";
	}

	public User getUserData(@ModelAttribute("username") String username) {//(Session için)
		User user = userService.findByUsername(username);
		return user;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET) // ilk-buraya-giriyor-error-kontrolleri-yapýyor-arka-kütüphaneler-ile-denetlemeler-yapýyor.(1)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid."); // Eðer-username-hatalýysa-kontrol-yap

		if (logout != null) //Logout'a-basarsa
			model.addAttribute("error", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET) // Spring-kütüphanesi-sayesinde-Session-yarattýk(3)
	public String welcome(@ModelAttribute("username") String username, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = getUserData(authentication.getName());
		model.addAttribute("user", user); 
		return "welcome";
	}

	@RequestMapping(value = "/getCityTemp", method = RequestMethod.GET)
	public String goToService(@RequestParam Map<String, String> params, Model model) {
		String sehir = params.get("sehir");
		String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + sehir
				+ ",usl&units=metric&appid=1004f69aa9506063496f7891597238be";

		JSONObject jsonObject = null;

		try {
			String json = JSONParser.getJSONFromUrl(weatherUrl);
			try {
				jsonObject = new JSONObject(json);
			} catch (JSONException e) {
				System.out.println("URI 'dan json okunamadý");
			}

			JSONObject mainObj = jsonObject.getJSONObject("main");
			tempNo = mainObj.getInt("temp");
			humidity = mainObj.getInt("humidity");

			JSONObject time = jsonObject.getJSONObject("dt");

		} catch (JSONException exception) {
			log.debug("Dýþtaki Try blogu patladý, dikkat et");
		}

		String city = sehir;

		String weather_temp = " Hava sýcaklýðý : " + tempNo; // model ekleyip
																// sayfaya
																// yazdýrmak
																// için,
		String weather_humidity = "Nem : %" + humidity;

		model.addAttribute("weather_temp", weather_temp); // weather'ýn içine
															// sehir ve Tempi
															// çekiyoruz ve
															// modele atýyoruz.
		model.addAttribute("weather_humidity", weather_humidity);
		model.addAttribute("city", city);

		log.debug(sehir + " Hava Sýcaklýðý : " + tempNo);

		return "welcome";

	}
	
	public static void main(String[] args) {
		Date d = new Date(1487257200);
		System.out.println("Date :" + d);
	}

}
