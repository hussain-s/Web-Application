package com.shabbir.shabbir.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Payment")
public class Payment {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "paymentId", unique=true, nullable = false)
	private Integer paymentId;
	@Column(name = "paymentMethod", nullable = false, length = 45)
	private String paymentmethod;
	@Column(name = "amount", nullable = false, length = 45)
	private int amount;
	
	 @JoinColumn(name="ticketId")
	    private Integer ticketId;
	
	public Payment() {
		
	}
	

	public Payment(String paymentmethod, int amount, Integer ticketId) {
	
		
		this.paymentmethod = paymentmethod;
		this.amount = amount;
		this.ticketId = ticketId;
	}


	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	
	
	
}
