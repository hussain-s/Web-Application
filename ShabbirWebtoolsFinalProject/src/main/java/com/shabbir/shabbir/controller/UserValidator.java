package com.shabbir.shabbir.controller;

import com.shabbir.shabbir.pojo.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.ValidationUtils;

@Component
public class UserValidator implements Validator {

	private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(JPG|png|gif|bmp))$)";

	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Pattern pattern = Pattern.compile(IMAGE_PATTERN);
		Matcher matcher;
		MultipartFile photo;
		
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "error.invalid.user", "Age Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.user", "Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "error.invalid.user", "Phone Number Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photo", "error.invalid.photo", "Photo Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "error.invalid.status", "Status Required");
		
//		User user = (User) obj;
		// apply null check for photo
		
//		photo = user.getPhoto();
//		
//		matcher = pattern.matcher(photo.getOriginalFilename());
//
//		// check for photo
//		if (0 == photo.getSize()) {
//			errors.rejectValue("photo", "Test", "File is empty");
//		}
//		if (!matcher.matches()) {
//			errors.rejectValue("photo", "Test", "Invalid Image Format");
//		}
//
//		if (5000000 < photo.getSize()) {
//			errors.rejectValue("photo", "Test", "File size is over 5mb !");
//		}
	}
}
