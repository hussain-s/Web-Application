package com.shabbir.shabbir.pojo;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="user")
@PrimaryKeyJoinColumn(name="personId")
public class User extends Person {
	
	
	
	@Column(name = "userName", nullable = false, length = 45)
	private String userName;
	@Column(name = "status")
	private String status;
	
	@Column(name = "password", nullable = false, length = 45)
	private String password;
	@Column(name = "photoName", length = 500)
	private String photoName;
	@Transient
	private MultipartFile photo;
//	@OneToMany(fetch=FetchType.EAGER,mappedBy="user")
//	private Set<Ticket> ticket;

	public User(){
		
	}
	
//	public User(Set<Ticket> ticket){
//		this.ticket= new HashSet<Ticket>();
//	}
public User(String userName, String password,String photoName, String status) {
		
		
		this.userName = userName;
		this.password = password;
		this.photoName=photoName;
		this.status=status;
		
		
	}
	
	


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}



	
	
	

}
