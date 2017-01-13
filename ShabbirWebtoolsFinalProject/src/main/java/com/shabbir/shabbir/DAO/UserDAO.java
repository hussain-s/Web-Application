package com.shabbir.shabbir.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.Statement;
import com.shabbir.shabbir.exception.AdException;
import com.shabbir.shabbir.pojo.Person;
import com.shabbir.shabbir.pojo.User;

@Component
public class UserDAO extends DAO {

	public UserDAO() {

	}

	public User get(String username, String password, String status) throws AdException {

		try {
			begin();

			Query q = getSession()
					.createQuery("from User where userName = :username and password = :password and status = :status");

			q.setString("username", username);
			q.setString("password", password);
			q.setString("status", status);

			User user = (User) q.uniqueResult();
			commit();
			return user;

		} catch (HibernateException e) {

			rollback();
			throw new AdException("Could not get user " + username, e);

		} finally {
			close();
		}

	}

	public User getUserName(String username) throws AdException {

		try {
			begin();

			Query q = getSession().createQuery("from User where userName = :username");

			q.setString("username", username);
			User user = (User) q.uniqueResult();
			commit();
			return user;

		} catch (HibernateException e) {

			rollback();
			throw new AdException("Could not get user " + username, e);

		} finally {
			close();
		}

	}

	public User create(String userName, String password, String photoName, String firstName, String lastName, int age,
			String email, String phoneNumber, String status) throws AdException {
		try {
			begin();

			User user = new User(userName, password, photoName, status);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setAge(age);
			user.setEmail(email);
			user.setPhoneNumber(phoneNumber);
			user.setStatus(status);

			getSession().save(user);

			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new AdException("Exception while creating user: " + e.getMessage());
		} finally {
			close();
		}
	}

	public int updateStatus(String status, String username) throws AdException {

		try {
			int rowcount;
			begin();
			Query query = getSession().createQuery("Update User set status = :status where userName = :username");
			query.setString("status", status);
			query.setString("username", username);
			rowcount = query.executeUpdate();
			System.out.println(rowcount);
			commit();
			return rowcount;

		} catch (HibernateException ex) {

			rollback();
			throw new AdException("Exception while updating user: " + ex.getMessage());
		} finally {
			close();
		}

	}

	public ArrayList<String> search() throws AdException {
		
		try {
			begin();
			Query query = getSession().createQuery("Select userName from User");
			@SuppressWarnings("unchecked")
			ArrayList<String> userDirecotry= (ArrayList<String>) query.list();
			commit();
			return userDirecotry;
		} catch (HibernateException ex) {

			rollback();
			throw new AdException("Exception while getting user: " + ex.getMessage());
		} finally {
			close();
		}
	}

}
