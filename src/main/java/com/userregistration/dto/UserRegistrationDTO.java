package com.userregistration.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.ToString;

@Data
public @ToString class UserRegistrationDTO {

	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee FirstName invalid")
	public String firstName;
	
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee LastName invalid")
	public String lastName;
	
	@NotEmpty(message = "DOB cannot be Empty")
	public String dOB;
	
	@Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Invalid Mobile Number")
	public String mobileNo;
	
	@NotEmpty(message = "Email Id cannot be Empty")
	public String emailId;
	
	@NotEmpty(message = "Password cannot be Empty")
	public String password;
	
	@NotEmpty(message = "Profile Pic cannot be Empty")
	public String profilepic;
}
