package com.userregistration.model;

import java.time.LocalDate;

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
	private int userid;
	
	@Column
	private String firstName;
	private String lastName;
	private LocalDate dOB;
	private String mobileNo;

	private LocalDate registeredTime = LocalDate.now(); 
	
	public UserRegistrationData() {}
	
	public UserRegistrationData(UserRegistrationDTO userDTO) {
		this.updateUserRegistration(userDTO);
	}
	
	public void updateUserRegistration(UserRegistrationDTO userDTO) {
		this.firstName = userDTO.firstName;
		this.lastName = userDTO.lastName;
		this.dOB = userDTO.dOB;
		this.mobileNo = userDTO.mobileNo;
	}
}
