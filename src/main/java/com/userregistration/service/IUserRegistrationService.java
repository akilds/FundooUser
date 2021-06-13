package com.userregistration.service;

import java.util.List;

import javax.validation.Valid;

import com.userregistration.dto.UserRegistrationDTO;
import com.userregistration.model.UserRegistrationData;
import com.userregistration.util.Response;

public interface IUserRegistrationService {

	List<UserRegistrationData> getAllUsers(String token);

	Response addUser(@Valid UserRegistrationDTO userRegistrationDTO);

	Response updateUser(String token, @Valid UserRegistrationDTO userRegistrationDTO);

	Response deleteUser(String token);

	Response verifyUser(String token);

	boolean verifyUserId(int userId);

}
