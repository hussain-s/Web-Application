package com.shabbir.shabbir.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;




@Entity
@Table(name="seat")

public class Seat {
//	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "ticket"))
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "seatId", unique=true, nullable = false)
	private Integer seatId;
	@Column(name = "seatName", nullable = false, length = 45)
	private String seatName;
	@Column(name = "seatType", nullable = false, length = 45)
	private String seatType;
	@Column(name = "seatStatus", nullable = false, length = 45)
	private String seatStatus;
	
//@OneToOne(fetch = FetchType.EAGER)
	@Column(name="ticketId")
	private Integer ticket;
//	@OneToOne(fetch = FetchType.LAZY,targetEntity=Ticket.class)
//	@PrimaryKeyJoinColumn(name="ticketId")
	
	
	public Seat(){
		
	}
	public Seat(String seatName,String seatType, String seatStatus, Integer ticket){
		this.seatName=seatName;
		this.seatType=seatType;
		this.seatStatus=seatStatus;
		this.ticket=ticket;
	}
	
	public Seat(String seatName,String seatType, String seatStatus){
		this.seatName=seatName;
		this.seatType=seatType;
		this.seatStatus=seatStatus;
		
	}
	
	public Integer getTicket() {
		return ticket;
	}
	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}
	public Integer getSeatId() {
		return seatId;
	}
	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}

	
	
	
	

}
