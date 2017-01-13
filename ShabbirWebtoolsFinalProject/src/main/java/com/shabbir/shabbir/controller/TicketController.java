package com.shabbir.shabbir.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shabbir.shabbir.DAO.TicketDAO;
import com.shabbir.shabbir.exception.AdException;
import com.shabbir.shabbir.pojo.MatchDetails;
import com.shabbir.shabbir.pojo.Seat;

@Controller
public class TicketController {

	@Autowired
	TicketDAO ticketDao;

	public TicketController() {

	}

	@RequestMapping(value = "graph.htm", method = RequestMethod.POST)
	public String ticketRevenue(HttpServletRequest request, HttpServletResponse response)
			throws AdException, JSONException, IOException {

		try {

			long boston = ticketDao.revenue("Boston");
			long newYork = ticketDao.revenue("NewYork");
			long seattle = ticketDao.revenue("Seattle");
			long sanDiego = ticketDao.revenue("SanDiego");

			JSONObject obj = new JSONObject();
			obj.put("boston", boston);
			obj.put("newYork", newYork);
			obj.put("seattle", seattle);
			obj.put("sanDiego", sanDiego);
			PrintWriter out = response.getWriter();
			out.println(obj);
			return null;

		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return null;
	}

	@RequestMapping(value = "/match.htm", method = RequestMethod.POST)
	public String handlePayment(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {

			String matchDay = request.getParameter("matchDay");
			String place = request.getParameter("place");

			if (matchDay != null && place != null) {

				ticketDao.createMatch(matchDay, place);
				JSONObject obj = new JSONObject();
				obj.put("addmatchMsg", "Match has been added successfully");
				PrintWriter out = response.getWriter();
				out.println(obj);
				return null;
			} else {

				JSONObject obj = new JSONObject();
				obj.put("addmatchMsg", "Match cannot be added");
				PrintWriter out = response.getWriter();
				out.println(obj);
				return null;
			}

		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "";
	}

	@RequestMapping(value = "/getmatch.htm", method = RequestMethod.POST)
	public ModelAndView matchfetch(HttpServletRequest request, HttpServletResponse response) throws AdException {

		List matchList = null;
		List listOfMatch = new ArrayList();
		ModelAndView mv = new ModelAndView();

		try {
			matchList = ticketDao.matchDateDisplay();
			if (matchList != null) {
				Iterator matchIterate = matchList.iterator();

				while (matchIterate.hasNext()) {
					MatchDetails m = (MatchDetails) matchIterate.next();
					listOfMatch.add(m);

				}

				request.setAttribute("listOfMatch", listOfMatch);
				mv.addObject(listOfMatch);
				mv.setViewName("MatchDetails");

			}
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return mv;
	}

	@RequestMapping(value = "/venue.htm", method = RequestMethod.POST)
	public String handleChangeSeat(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {

			String matchDate = request.getParameter("matchDate");

			if (matchDate != null) {

				String venue = ticketDao.getVenue(matchDate);
				JSONObject obj = new JSONObject();
				obj.put("addVenue", venue);
				PrintWriter out = response.getWriter();
				out.println(obj);
				return null;
			} else {

				JSONObject obj = new JSONObject();
				obj.put("noVenue", "No Such Match exist on this date");
				PrintWriter out = response.getWriter();
				out.println(obj);
				return null;
			}

		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "";
	}

	@RequestMapping(value = "/verifyDate.htm", method = RequestMethod.POST)
	public String handleDate(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {

			String matchDate = request.getParameter("matchDay");

			if (matchDate != null) {

				MatchDetails matchdetails = ticketDao.checkDate(matchDate);
				if (matchdetails == null) {
					JSONObject obj = new JSONObject();
					obj.put("uniqueDate", "New Match Date");
					PrintWriter out = response.getWriter();
					out.println(obj);
					return null;
				} else {

					JSONObject obj = new JSONObject();
					obj.put("nouniqueDate", "This date we are already playing");
					PrintWriter out = response.getWriter();
					out.println(obj);
					return null;
				}

			}
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "";
	}

}