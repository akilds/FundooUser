package com.userregistration.service;

import java.util.List;

import com.userregistration.dto.UserRegistrationDTO;
import com.userregistration.model.UserRegistrationData;

public interface IUserRegistrationService {

	List<UserRegistrationData> getUserRegistrationData();
	UserRegistrationData createUserRegistrationData(UserRegistrationDTO userRegistrationDTO);	
	UserRegistrationData updateUserRegistrationData(int empId, UserRegistrationDTO userRegistrationDTO);
	void deleteUserRegistrationData(int empId);
	UserRegistrationData getUserRegistrationDataById(int userId);
}
