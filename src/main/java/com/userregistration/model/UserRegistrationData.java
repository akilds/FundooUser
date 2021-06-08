package com.userregistration.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.userregistration.dto.UserRegistrationDTO;

import lombok.Data;

@Entity
@Table(name = "userTable")
public @Data class UserRegistrationData {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column
	private String firstName;
	private String lastName;
	private String dOB;
	private String mobileNo;
	private String emailId;
	private String password;
	private LocalDateTime registerDate = LocalDateTime.now();
	private LocalDateTime updatedDate;
	private boolean verify;
	private String profilepic; 
	
	public UserRegistrationData() {}
	
	public UserRegistrationData(int userid, String firstName, String lastName, String mobileNo,
			String emailid, String password, String dob, LocalDateTime registerDate,
			LocalDateTime updatedDate, boolean verify, String profilepic) {
		super();
		this.userId = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.emailId = emailid;
		this.password = password;
		this.dOB = dob;
		this.registerDate = registerDate;
		this.updatedDate = updatedDate;
		this.verify = verify;
		this.profilepic = profilepic;
	}
	
	public void updateUserRegistration(UserRegistrationDTO userDTO) {
		this.firstName = userDTO.firstName;
		this.lastName = userDTO.lastName;
		this.dOB = userDTO.dOB;
		this.mobileNo = userDTO.mobileNo;
		this.emailId = userDTO.emailId;
		this.password = userDTO.password;
		this.profilepic = userDTO.profilepic;
		this.updatedDate = LocalDateTime.now();
	}
}
