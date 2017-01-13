package com.shabbir.shabbir.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import javax.persistence.Table;

@Entity
@Table(name="ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ticketId", unique=true, nullable = false)
	private Integer ticketId;
	
	
	@Column(name = "date")
	private String date;
	@Column(name = "venue")
	private String venue;
	@Column(name = "price")
	private int price;
	@Column(name = "seatName",nullable = false)
	private String seatName;
	
//	@ManyToOne(fetch = FetchType.EAGER,targetEntity=User.class)
	@Column(name="personId")
    private Integer user;
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name="seatId")
//	private Integer seat;
	
	public Ticket() {
		
	}
	public Ticket(String date, int price, String venue,String seatName){
		
		this.date=date;
		this.price=price;
		this.venue=venue;
		this.seatName=seatName;
		
		
	}

	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}



	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
//	public Integer getSeat() {
//		return seat;
//	}
//	public void setSeat(Integer seat) {
//		this.seat = seat;
//	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	
	
	
}
