package com.shabbir.shabbir.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.shabbir.shabbir.exception.AdException;
import com.shabbir.shabbir.pojo.Payment;
import com.shabbir.shabbir.pojo.Seat;
import com.shabbir.shabbir.pojo.Ticket;

@Component
public class PaymentDAO extends DAO {

	public PaymentDAO(){
		
	}
	
	
	public Payment create(String paymentMethod, int amount, int ticketId) throws AdException {
		try {
			
			begin();

			Payment payment = new Payment(paymentMethod,amount,ticketId);

			getSession().save(payment);

			commit();
			return payment;

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while creating seat: " + e.getMessage());
		}
		finally {
			close();
		}
	}
	
	public void delete(int ticketId) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("Delete from Payment where ticketId = :ticketId");

			q.setInteger("ticketId",ticketId);
			q.executeUpdate();

			commit();
			//return ticket.getTicketId();

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while creating seat: " + e.getMessage());
		}
		finally {
			close();
		}
	}
}
