package com.aksigorta.account.validator;

import com.aksigorta.account.model.User;
import com.aksigorta.account.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) { // (Registration-yaparken-b�t�n-fieldlar-dolunca-ilk-buraya-gelir-kontrolleriyapar(1))
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (user.getUsername().length() < 8 || user.getUsername().length() > 32) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "NotEmpty");
		if (user.getFirstname().length() < 1 || user.getFirstname().length() > 32) {
			errors.rejectValue("firstname", "Size.userForm.firstname");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty");
		if (user.getSurname().length() < 1 || user.getSurname().length() > 32) {
			errors.rejectValue("surname", "Size.userForm.surname");
		}
	}
}
