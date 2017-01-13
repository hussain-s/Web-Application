package com.shabbir.shabbir.controller;

import java.io.File;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shabbir.shabbir.controller.UserValidator;
import com.shabbir.shabbir.DAO.*;
import com.shabbir.shabbir.exception.AdException;
import com.shabbir.shabbir.pojo.Seat;
import com.shabbir.shabbir.pojo.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.SimpleFormController;

@SuppressWarnings("deprecation")
@Controller
// @RequestMapping("/help.htm")
public class UserController extends SimpleFormController {

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@Autowired
	UserDAO userDAO;

	public UserController() {

	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/help.htm", method = RequestMethod.POST)
	protected String handleRequest(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user, BindingResult result) throws Exception {

		String un = request.getParameter("u");
		if ((check(un) == true) && un!=null) {

			JSONObject obj = new JSONObject();
			obj.put("matchMsg","Username already exist");
			PrintWriter out = response.getWriter();
			out.println(obj);
			return null;
		} else if ((check(un) == false)&& un!=null) {

			JSONObject obj = new JSONObject();
			obj.put("nomatchMsg","Unique Userame");
			PrintWriter out = response.getWriter();
			out.println(obj);
			return null;

		} else if (un == null) {
			validator.validate(user, result);

			if (result.hasErrors()) {

				return "index";
			}

			try {

				File file;
				String check = File.separator; // Checking if system is linux
												// based
												// or windows based by checking
												// seprator used.
				String path = null;
				if (check.equalsIgnoreCase("\\")) {
					path = getServletContext().getRealPath("").replace("build\\", "");

				}

				if (check.equalsIgnoreCase("/")) {
					path = getServletContext().getRealPath("").replace("build/", "");
					path += "/"; // Adding trailing slash for Mac systems.

				}

				// UserDAO userDAO = new UserDAO();

				if (user.getPhoto() != null) {
					String fileNameWithExt = System.currentTimeMillis() + user.getPhoto().getOriginalFilename();
					file = new File(path + fileNameWithExt);

					// application path
					String context = getServletContext().getContextPath();

					user.getPhoto().transferTo(file);

					user.setPhotoName(context + "/" + fileNameWithExt);
					System.out.println("insert in DAO method");
					userDAO.create(user.getUserName(), user.getPassword(), user.getPhotoName(), user.getFirstName(),
							user.getLastName(), user.getAge(), user.getEmail(), user.getPhoneNumber(),
							user.getStatus());

					return "created";

					// DAO.close();
				}
			} catch (AdException e) {
				System.out.println("Exception: " + e.getMessage());
			}

		}
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result) {

		return "index";
	}

	@RequestMapping(value = "/welcome.htm", method = RequestMethod.POST)
	public String welcome(HttpServletRequest request, HttpServletResponse response) {

		return "index";
	}

	public Boolean check(String name) throws Exception {
		if (name != null) {

			ArrayList<String> us = userDAO.search();

			for (String u : us) {
				if (u.equals(name)) {
					return true;
				}
			}

		}

		return false;
	}

}
