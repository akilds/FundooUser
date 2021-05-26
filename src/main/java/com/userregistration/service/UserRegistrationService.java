package com.userregistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userregistration.dto.UserRegistrationDTO;
import com.userregistration.exception.UserRegistrationException;
import com.userregistration.model.UserRegistrationData;
import com.userregistration.repository.UserRegistrationRepository;

@Service
public class UserRegistrationService implements IUserRegistrationService{

	@Autowired
	private UserRegistrationRepository userRegistrationRepository;
	
	@Override
	public List<UserRegistrationData> getUserRegistrationData() {
		return userRegistrationRepository.findAll();
	}

	@Override
	public UserRegistrationData getUserRegistrationDataById(int empId) {
		return userRegistrationRepository
			   .findById(empId)
			   .orElseThrow(() -> new UserRegistrationException("Employee Not Found"));
	}

	@Override
	public UserRegistrationData createUserRegistrationData(UserRegistrationDTO userRegistrationDTO) {
		UserRegistrationData userData = null;
		userData = new UserRegistrationData(userRegistrationDTO);
		return userRegistrationRepository.save(userData);
	}

	@Override
	public UserRegistrationData updateUserRegistrationData(int userId, UserRegistrationDTO userRegistrationDTO) {
		UserRegistrationData userData = this.getUserRegistrationDataById(userId);
		userData.updateUserRegistration(userRegistrationDTO);
		return userRegistrationRepository.save(userData);
	}

	@Override
	public void deleteUserRegistrationData(int userId) {
		UserRegistrationData userData = this.getUserRegistrationDataById(userId);
		userRegistrationRepository.delete(userData);
	}
}
