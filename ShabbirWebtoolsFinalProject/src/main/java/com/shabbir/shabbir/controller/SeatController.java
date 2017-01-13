package com.shabbir.shabbir.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import com.shabbir.shabbir.DAO.SeatDAO;
import com.shabbir.shabbir.DAO.TicketDAO;
import com.shabbir.shabbir.DAO.UserDAO;
import com.shabbir.shabbir.exception.AdException;
import com.shabbir.shabbir.pojo.Seat;
import com.shabbir.shabbir.pojo.Ticket;
import com.shabbir.shabbir.pojo.User;

@Controller
public class SeatController {

	@Autowired
	TicketDAO ticketDao;
	@Autowired
	SeatDAO seatDao;
	@Autowired
	UserDAO userDao;

	public SeatController() {

	}

	@RequestMapping(value = "/seat.htm", method = RequestMethod.POST)
	public String handleSeatRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String seatName = request.getParameter("seatName");
		if (seatName.matches("^[A-Z]{1}[0-9]{3,4}$"))
			
		{
			String seatButton = request.getParameter("check");
			if ((checkSeat(seatName) == true) && seatButton != null) {

				JSONObject obj = new JSONObject();
				obj.put("seatmatchMsg", "SeatName already exist");
				PrintWriter out = response.getWriter();
				out.println(obj);
				return null;
			} else if ((checkSeat(seatName) == false) && seatButton != null) {

				JSONObject obj = new JSONObject();
				obj.put("noseatmatchMsg", "Unique Seatname");
				PrintWriter out = response.getWriter();
				out.println(obj);
				return null;

			}

			else if (seatButton == null) {
				try {
					String matchDate = request.getParameter("matchDate");
					int price = Integer.parseInt(request.getParameter("price"));
					String venue = request.getParameter("venue");

					String seatType = request.getParameter("seatType");
					String seatStatus = request.getParameter("seatStatus");

					if (seatName != null && seatDao.Count() <= 100 && matchDate != null && price != 0
							&& venue != null) {

						Ticket ticket = ticketDao.create(matchDate, price, venue, seatStatus);

						// seatDao.create(seatName, seatType, seatStatus);
						seatDao.create(seatName, seatType, seatStatus, ticket.getTicketId());
						JSONObject obj = new JSONObject();
						obj.put("successmsg", "Seat has been added successfully");
						PrintWriter out = response.getWriter();
						out.println(obj);
						return null;
					} else {

						JSONObject obj = new JSONObject();
						obj.put("successmsg", "Seat cannot be added");
						PrintWriter out = response.getWriter();
						out.println(obj);
						return null;
					}
				} catch (AdException e) {
					System.out.println("Exception: " + e.getMessage());
				}
			}
		}
		else{
			JSONObject obj = new JSONObject();
			obj.put("seatmatchMsg", "seat name must not have special characters");
			PrintWriter out = response.getWriter();
			out.println(obj);
			return null;
		}
		return "";

	}

	@RequestMapping(value = "/displayseat.htm", method = RequestMethod.POST)
	public ModelAndView viewSeatRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("CheckSeat");
		ModelAndView mv = new ModelAndView();

		List seatList = null;
		List listOfSeat = new ArrayList();
		List ticketList = null;
		List listOfTicket = new ArrayList();
		List userTicketList = null;
		List listOfUserTicket = new ArrayList();

		try {
			if (action == null) {
				seatList = seatDao.seatDisplay();
				if (seatList != null) {
					Iterator seatIterate = seatList.iterator();

					while (seatIterate.hasNext()) {
						Seat s = (Seat) seatIterate.next();
						listOfSeat.add(s);

					}
					ticketList = ticketDao.ticketDisplay();
					if (ticketList != null) {

						Iterator ticketIterate = ticketList.iterator();
						while (ticketIterate.hasNext()) {

							Ticket t = (Ticket) ticketIterate.next();
							listOfTicket.add(t);
						}

					}

					request.setAttribute("listOfSeat", listOfSeat);
					mv.addObject(listOfSeat);
					request.setAttribute("listOfTicket", listOfTicket);
					mv.addObject(listOfTicket);
					mv.setViewName("UserView");

				}
			} else if (action != null) {
				String userName = action;
				User user = userDao.getUserName(userName);
				userTicketList = ticketDao.userTicketList(user.getPersonId());
				if (userTicketList != null) {

					Iterator useriterate = userTicketList.iterator();
					while (useriterate.hasNext()) {

						Ticket tick = (Ticket) useriterate.next();
						listOfUserTicket.add(tick);
					}
				}
				request.setAttribute("userTicketList", userTicketList);
				mv.addObject(userTicketList);
				mv.setViewName("UserView");

			}
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return mv;
	}

	public Boolean checkSeat(String name) throws Exception {
		if (name != null) {

			ArrayList<String> se = seatDao.search();

			for (String s : se) {
				if (s.equals(name)) {
					return true;
				}
			}

		}

		return false;
	}

}
