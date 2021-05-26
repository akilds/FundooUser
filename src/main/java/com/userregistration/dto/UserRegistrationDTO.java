package com.userregistration.dto;

import java.time.LocalDate;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;

public @ToString class UserRegistrationDTO {

	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee FirstName invalid")
	public String firstName;
	
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee LastName invalid")
	public String lastName;
	
	@JsonFormat(pattern="dd MMM yyyy")
	public LocalDate dOB;
	
	@Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Invalid Mobile Number")
	public String mobileNo;
}
