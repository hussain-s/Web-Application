package com.shabbir.shabbir.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.shabbir.shabbir.exception.AdException;
import com.shabbir.shabbir.pojo.MatchDetails;
import com.shabbir.shabbir.pojo.Seat;
import com.shabbir.shabbir.pojo.Ticket;

@Component
public class TicketDAO extends DAO {
	
	public Ticket create(String date, int price, String venue,String seatName) throws AdException {
		try {
				begin();
			Ticket ticket = new Ticket(date, price, venue,seatName);

			getSession().save(ticket);

			commit();
			return ticket;

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while creating seat: " + e.getMessage());
		}
		finally {
			close();
		}
	}
	
	public int get(String seatName) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Ticket where seatName = :seatName");

			q.setString("seatName", seatName);
			Ticket ticket = (Ticket)q.uniqueResult();

			commit();
			return ticket.getTicketId();

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while creating seat: " + e.getMessage());
		}
		finally {
			close();
		}
	}
	public List ticketDisplay()throws AdException{
		try{
			begin();
			List  allTicket ;
			Query q = getSession().createQuery("from Ticket where seatName = 'Empty'");
			allTicket = q.list();
			commit();
			return allTicket;
		}
		
		catch (HibernateException ex) {
			rollback();
			throw new AdException("Exception while getting all the seat: " + ex.getMessage());
		}
		finally {
			close();
		}
	}
public int updatePersonId(int personId,String seatName,int ticketId) throws AdException{
		
		try{
			int rowcount;
			begin();
			Query query = getSession().createQuery("Update Ticket set personId = :personId,seatName = :seatName where ticketId = :ticketId");
			query.setInteger("personId",personId);
			query.setString("seatName", seatName);
			query.setInteger("ticketId",ticketId);
			rowcount=query.executeUpdate();
			System.out.println(rowcount);
			commit();
			return rowcount;
			
		}
		catch(HibernateException ex){
			
			rollback();
			throw new AdException("Exception while updating user: " + ex.getMessage());
		}
		finally {
			close();
		}
		
	}

public List userTicketList(Integer personId)throws AdException{
	try{
		begin();
		List  allTicket ;
		Query q = getSession().createQuery("from Ticket where personId = :personId");
		q.setInteger("personId", personId);
		allTicket = q.list();
		commit();
		return allTicket;
	}
	
	catch (HibernateException ex) {
		rollback();
		throw new AdException("Exception while getting all the tickets of that user: " + ex.getMessage());
	}
	finally {
		close();
	}
}

public Ticket get(int ticketId) throws AdException {
	
	try {
		begin();
		
		Query q = getSession().createQuery("from Ticket where ticketId = :ticketId");
		
		q.setInteger("ticketId", ticketId);
		

		Ticket ticket = (Ticket) q.uniqueResult();
	   commit();
		return ticket;
		
		
	} catch (HibernateException e) {

		rollback();
		throw new AdException("Could not get ticketId " + ticketId, e);

	}
	finally {
		close();
	}
	

}
public int removePersonId(String seatName,int ticketId) throws AdException{
	
	try{
		int rowcount;
		begin();
		Query query = getSession().createQuery("Update Ticket set personId = :personId,seatName = :seatName where ticketId = :ticketId");
		query.setParameter("personId",null,StandardBasicTypes.INTEGER);
		query.setString("seatName", seatName);
		query.setInteger("ticketId",ticketId);
		rowcount=query.executeUpdate();
	    commit();
		return rowcount;
		
	}
	catch(HibernateException ex){
		
		rollback();
		throw new AdException("Exception while updating user: " + ex.getMessage());
	}
	finally {
		close();
	}
	
}

public long revenue(String venue)throws AdException{

	try{
		long amount;
		begin();
		Query query =getSession().createQuery("Select SUM(price) from Ticket where venue =:venue and seatName ='Occupied'");
		query.setString("venue", venue);
		amount = (Long)query.uniqueResult();
		commit();
		return amount;
	}
	catch(HibernateException e){
		rollback();
		throw new AdException("Exception while getting count :");
	}
	finally{
		close();
	}
}

public MatchDetails createMatch(String matchDate,String venue) throws AdException {
	try {
			begin();
		MatchDetails match = new MatchDetails(matchDate,venue);

		getSession().save(match);

		commit();
		return match;

	} catch (HibernateException e) {
		rollback();
		throw new AdException("Exception while creating match: " + e.getMessage());
	}
	finally {
		close();
	}
}

public List matchDateDisplay()throws AdException{
	try{
		begin();
		List allmatch;
		Query q = getSession().createQuery("from MatchDetails");
		
		allmatch = q.list();
		commit();
		return allmatch;
	}
	
	catch (HibernateException ex) {
		rollback();
		throw new AdException("Exception while getting all the seat: " + ex.getMessage());
	}
	finally {
		close();
	}
}

public String getVenue(String matchDate) throws AdException {
	try {
		begin();
		Query q = getSession().createQuery("from MatchDetails where matchDate = :matchDate");

		q.setString("matchDate", matchDate);
		MatchDetails match = (MatchDetails)q.uniqueResult();

		commit();
		return match.getVenue();

	} catch (HibernateException e) {
		rollback();
		throw new AdException("Exception while creating seat: " + e.getMessage());
	}
	finally {
		close();
	}
}

public MatchDetails checkDate(String matchDate) throws AdException {
	try {
		begin();
		Query q = getSession().createQuery("from MatchDetails where matchDate = :matchDate");

		q.setString("matchDate",matchDate);
		MatchDetails match = (MatchDetails)q.uniqueResult();

		commit();
		return match;

	} catch (HibernateException e) {
		rollback();
		throw new AdException("Exception while creating seat: " + e.getMessage());
	}
	finally {
		close();
	}
}


}
 