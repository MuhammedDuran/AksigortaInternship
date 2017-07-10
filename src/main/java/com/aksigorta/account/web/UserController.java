package com.aksigorta.account.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.aksigorta.account.model.User;
import com.aksigorta.account.service.SecurityService;
import com.aksigorta.account.service.UserService;
import com.aksigorta.account.validator.UserValidator;

@Controller
@SessionAttributes("user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

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

	public User getUserData(@ModelAttribute("username") String username) {// (Session
																			// için)
		User user = userService.findByUsername(username);
		return user;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET) // ilk-buraya-giriyor-error-kontrolleri-yapıyor-arka-kütüphaneler-ile-denetlemeler-yapıyor.(1)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid."); // Eğer-username-hatalıysa-kontrol-yap

		if (logout != null) // Logout'a-basarsa
			model.addAttribute("error", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET) // Spring-kütüphanesi-sayesinde-Session-yarattık(3)
	public String welcome(@ModelAttribute("username") String username, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = getUserData(authentication.getName());
		model.addAttribute("user", user);
		return "welcome";
	}

}
