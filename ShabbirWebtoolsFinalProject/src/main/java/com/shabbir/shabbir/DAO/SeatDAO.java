package com.shabbir.shabbir.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.shabbir.shabbir.exception.AdException;
import com.shabbir.shabbir.pojo.Seat;
import com.shabbir.shabbir.pojo.Ticket;
import com.shabbir.shabbir.pojo.User;

@Component
public class SeatDAO extends DAO {

	public SeatDAO() {

	}

	//public Seat create(String seatName, String seatStatus, String seatType) throws AdException {
		public Seat create(String seatName, String seatStatus, String seatType,Integer ticketId) throws AdException {
		try {
			
			begin();

			Seat seat = new Seat(seatName, seatStatus, seatType, ticketId);

			getSession().save(seat);

			commit();
			return seat;

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while creating seat: " + e.getMessage());
		}
		finally {
			close();
		}
	}

	public int Count() throws AdException {

		try {
			int totalseat = 0;
			begin();
			totalseat = ((Long) getSession().createQuery("select count(*) from Seat").uniqueResult()).intValue();

			return totalseat;

		}

		catch (HibernateException ex) {
			rollback();
			throw new AdException("Exception while getting number of seat: " + ex.getMessage());
		}
		finally {
			close();
		}

	}
	
	public List seatDisplay()throws AdException{
		try{
			begin();
			List  allSeat ;
			Query q = getSession().createQuery("from Seat where seatStatus = 'Empty'");
			allSeat = q.list();
			commit(); 
			return allSeat;
		}
		
		catch (HibernateException ex) {
			rollback();
			throw new AdException("Exception while getting all the seat: " + ex.getMessage());
		}
		finally {
			close();
		}
	}
	public int seatSold( String seatStatus,String seatName)throws AdException{
		
	try{
		begin();
		
		Query query = getSession().createQuery("Update Seat set seatStatus = :seatStatus where seatName = :seatName");
		query.setString("seatStatus", seatStatus);
		query.setString("seatName", seatName);
		int rowcount=query.executeUpdate();
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
	
public Seat get(int ticketId) throws AdException {
		
		try {
			begin();
			
			Query q = getSession().createQuery("from Seat where ticketId = :ticketId");
			
			q.setInteger("ticketId", ticketId);
			
	
			Seat seat = (Seat) q.uniqueResult();
		   commit();
			return seat;
			
			
		} catch (HibernateException e) {

			rollback();
			throw new AdException("Could not get ticketId " + ticketId, e);

		}
		finally {
			close();
		}
		

	}

public ArrayList<String> search() throws AdException {
	
	try {
		begin();
		Query query = getSession().createQuery("Select seatName from Seat");
		@SuppressWarnings("unchecked")
		ArrayList<String> seatDirecotry= (ArrayList<String>) query.list();
		commit();
		return seatDirecotry;
	} catch (HibernateException ex) {

		rollback();
		throw new AdException("Exception while getting seat: " + ex.getMessage());
	} finally {
		close();
	}
}

}


