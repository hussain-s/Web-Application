package com.shabbir.shabbir.controller;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shabbir.shabbir.DAO.PaymentDAO;
import com.shabbir.shabbir.DAO.SeatDAO;
import com.shabbir.shabbir.DAO.TicketDAO;
import com.shabbir.shabbir.DAO.UserDAO;
import com.shabbir.shabbir.exception.AdException;
import com.shabbir.shabbir.pojo.Payment;
import com.shabbir.shabbir.pojo.Seat;
import com.shabbir.shabbir.pojo.User;

@Controller
public class PaymentController {

	@Autowired
	PaymentDAO paymentDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	TicketDAO ticketDAO;
	
	@Autowired
	SeatDAO seatDAO;
	public PaymentController() {
		
	}
	
	@RequestMapping(value = "/payment1.htm", method = RequestMethod.POST)
	public String handlePayment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			
			String paymentMethod = request.getParameter("paymentMethod");
			String seatName=request.getParameter("seatName");
			int amount = Integer.parseInt(request.getParameter("amount"));
			int ticketId = Integer.parseInt(request.getParameter("ticketId"));
			String userName = request.getParameter("userName");
			
			if(paymentMethod!=null && amount!=0 && ticketId!=0 && userName !=null){
				
			Payment pay= paymentDAO.create(paymentMethod, amount, ticketId);
			String seatStatus="Occupied";
			seatDAO.seatSold(seatStatus,seatName);
			User user= userDAO.getUserName(userName);
			ticketDAO.updatePersonId(user.getPersonId(),seatStatus ,ticketId);
			if(pay!=null){
				request.setAttribute("ticketId", ticketId);
				return "DownTicket";
			}
			else{
				
			}
			}
		}
		catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		return "";
	}
	
	
	@RequestMapping(value = "/email.htm", method = RequestMethod.POST)
	public String emailRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String user = request.getParameter("userName");
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));
		User u= userDAO.getUserName(user);
		Seat s=seatDAO.get(ticketId);
		if(u.getEmail()!=null){
		
		Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable","true");
	        properties.put("mail.smtp.host","smtp.gmail.com");
	        properties.put("mail.smtp.port","587");
	        properties.put("mail.smtp.host","smtp.gmail.com");
		
	        final String username = "shabbirmaloobhai.72@gmail.com";
	        final String password ="troubleshoot";
	        String  fromEmailAddress = "shabbirmaloobhai.72@gmail.com";
	        
	        
	        Session session = Session.getDefaultInstance(properties, new Authenticator() {


	            
	            @Override
	              protected PasswordAuthentication getPasswordAuthentication() {
	                  return new PasswordAuthentication(username,password);
	              } 
	              
	  }); 
	        Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmailAddress));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(u.getEmail()));
            message.setSubject("Ticket for Boston Football Club");
            message.setText("Seat Name :"+ s.getSeatName()+System.lineSeparator()+" Seat Type :" +s.getSeatType());
            
            Transport.send(message);
            JSONObject obj = new JSONObject();
			obj.put("successmsg", "Email has been send successfully at :"+u.getEmail());
			PrintWriter out = response.getWriter();
			out.println(obj);
			return null;
            
            
		}     
	        
		return null;
	}
	
	
	
	@RequestMapping(value="Payment.htm",method = RequestMethod.GET)
	public String doHandle(HttpServletRequest request, HttpServletResponse response) {
		
		//String j = request.getParameter("val");
		String price = request.getParameter("price");
		String seatName=request.getParameter("seatName");
		String ticketId = request.getParameter("ticketId");
		request.setAttribute("price",price);
		request.setAttribute("ticketId", ticketId);
		request.setAttribute("seatName",seatName);
		
		
		return "Payment";
	}
	
	
}
