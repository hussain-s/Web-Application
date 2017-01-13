package com.shabbir.shabbir.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.shabbir.shabbir.DAO.UserDAO;
import com.shabbir.shabbir.exception.AdException;
import com.shabbir.shabbir.pojo.User;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("loginValidator")
	LoginValidator validator;

	@Autowired
	UserDAO userDAO;

	public LoginController() {

	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public String handleLoginRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
				
				ModelAndView mv = new ModelAndView();
			
			
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String status = request.getParameter("status");
			String value=request.getParameter("Signin");
			
			if(value.equals("Signin")){
				HttpSession session = request.getSession();
			if (session.getAttribute("userName") != null) {
				
				return "UserView";
			}
			

			if (userName != null && password != null) {

				User u = userDAO.get(userName, password, status);

				if (u != null) {
					if (u.getStatus().equalsIgnoreCase("user")) {
						
						mv.addObject("u", u);
						session.setAttribute("userName", u.getUserName());
						

						return "UserView";
					} else if (u.getStatus().equalsIgnoreCase("manager")) {
						mv.addObject("u", u);
						session.setAttribute("userName", u.getUserName());

						return "ManagerView";
					} else if (u.getStatus().equalsIgnoreCase("admin")) {
						
						mv.addObject("u", u);
						session.setAttribute("userName", u.getUserName());

						return "AdminView";
					}
				}

				else {
					JSONObject obj = new JSONObject();
					obj.put("Errormsg", "No Such username:"+ userName + " and password exist");
					PrintWriter out = response.getWriter();
					out.println(obj);
					return null;

				}
				
					
				

			}
		}
			if(value.equals("logout")){
				HttpSession session = request.getSession(true);
                session.invalidate();
                return "Logout";
			}
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "signup";
	}

	@RequestMapping(value = "/addmanager.htm", method = RequestMethod.POST)
	public ModelAndView makeManagerHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		ModelAndView mv = new ModelAndView();
		try {

			

			if (userName != null) {
				User u = userDAO.getUserName(userName);
				if (u != null) {
					request.setAttribute("u", u);
					mv.addObject(u);
					mv.setViewName("AdminView");

				} else {
					JSONObject obj = new JSONObject();
					obj.put("Errormsg", "No Such username exist");
					PrintWriter out = response.getWriter();
					out.println(obj);
					return null;

				}
			}
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return mv;
	}
/*
//	@RequestMapping(value = "/updatemanager.htm", method = RequestMethod.POST)
//	public String updateStatus(HttpServletRequest request, HttpServletResponse response,
//			@ModelAttribute("user") User user, BindingResult result) throws Exception {
//
//		try {
//				
//			// validator.validate(user, result);
//
//			// if (result.hasErrors()) {
//			//
//			// return "AdminView";
//			// }
//			if (user.getUserName() != null && user.getStatus() != null) {
//
//				int i = userDAO.updateStatus(user.getStatus(), user.getUserName());
//				if (i == 1) {
//					request.setAttribute("managercreated", "manager created successfully");
//					return "AdminView";
//				} else {
//					JSONObject obj = new JSONObject();
//					obj.put("Errormsg", "Manager cannot be added please check the status");
//					PrintWriter out = response.getWriter();
//					out.println(obj);
//					return null;
//				}
//
//			} else {
//
//				JSONObject obj = new JSONObject();
//				obj.put("Errormsg", "Status column should be manager only");
//				PrintWriter out = response.getWriter();
//				out.println(obj);
//				return null;
//
//			}
//
//		} catch (AdException e) {
//			System.out.println("Exception: " + e.getMessage());
//		}
//
//		return "AdminView";
//
//	}*/

	@RequestMapping(value = "/updatemanager.htm", method = RequestMethod.POST)
	public String updateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
				String userName = request.getParameter("userName");
				String status = request.getParameter("status");
			
			if (userName != null && status.equalsIgnoreCase("manager")) {

				int i = userDAO.updateStatus(status, userName);
				if (i == 1) {
					JSONObject obj = new JSONObject();
					obj.put("msg", "Manager created successfully");
					PrintWriter out = response.getWriter();
					out.println(obj);
					return null;
				} else {
					JSONObject obj = new JSONObject();
					obj.put("msg", "Manager cannot be added please check the status");
					PrintWriter out = response.getWriter();
					out.println(obj);
					return null;
				}

			} else {

				JSONObject obj = new JSONObject();
				obj.put("msg", "Status column should be manager only");
				PrintWriter out = response.getWriter();
				out.println(obj);
				return null;

			}

		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "AdminView";

	}

}
