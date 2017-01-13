package com.shabbir.shabbir.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shabbir.shabbir.DAO.PaymentDAO;
import com.shabbir.shabbir.DAO.SeatDAO;
import com.shabbir.shabbir.DAO.TicketDAO;
import com.shabbir.shabbir.exception.AdException;
import com.shabbir.shabbir.pojo.Seat;
import com.shabbir.shabbir.pojo.Ticket;

@Controller
public class CancelController {

	@Autowired
	SeatDAO seatDAO;

	@Autowired
	TicketDAO ticketDAO;
	
	@Autowired
	PaymentDAO paymentDAO;

	public CancelController() {

	}

	@RequestMapping(value = "Cancel.htm", method = RequestMethod.GET)
	public String doHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			String tickId = request.getParameter("ticketId");
			int ticketId = Integer.parseInt(tickId);

		 	Seat seat = seatDAO.get(ticketId);
		 	Ticket ticket = ticketDAO.get(ticketId);

			request.setAttribute("seat",seat);
			request.setAttribute("ticket", ticket);

		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "Cancel";
	}
	
	@RequestMapping(value="cancelled.htm",method = RequestMethod.POST)
	public String cancelTicket(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			
			int ticketId = Integer.parseInt(request.getParameter("ticketId"));
			String seatName=request.getParameter("seatName");
			if(ticketId!=0 && seatName !=null){
				String seatStatus="Empty";
				int t =ticketDAO.removePersonId(seatStatus ,ticketId);
				paymentDAO.delete(ticketId);
				int s= seatDAO.seatSold(seatStatus,seatName);
				
				
				
				if(s!=0 && t!=0){
					
					return "TicketCancelled";
				}
				
			}
			else{
				return "index";
			}
			
		}
		catch(AdException e){
			
			System.out.println("Exception: " + e.getMessage());
		}
		
		return "";
		
	}
}
