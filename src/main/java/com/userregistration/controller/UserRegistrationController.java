package com.userregistration.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userregistration.dto.ResponseDTO;
import com.userregistration.dto.UserRegistrationDTO;
import com.userregistration.model.UserRegistrationData;
import com.userregistration.service.IUserRegistrationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/userregistration")
@Slf4j
public class UserRegistrationController {

	@Autowired
	private IUserRegistrationService userRegistrationService;
	
	@GetMapping({"","/","/get"})
	public ResponseEntity<ResponseDTO> getAllUsersRegistered() {
		List<UserRegistrationData> userList = null;
		userList = userRegistrationService.getUserRegistrationData();
		ResponseDTO respDTO = new ResponseDTO("Get Call Success", userList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<ResponseDTO> getUserRegistrationData(@PathVariable("userId") int userId) {
		UserRegistrationData userData = userRegistrationService.getUserRegistrationDataById(userId);
		ResponseDTO respDTO = new ResponseDTO("Get Call for ID Successful", userData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> addUserRegistrationData(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
		log.debug("User DTO : " + userRegistrationDTO.toString());
		UserRegistrationData userRegistrationData = userRegistrationService.createUserRegistrationData(userRegistrationDTO);
		ResponseDTO respDTO = new ResponseDTO("Created Employee Payroll Data Successfully", userRegistrationData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("userId") int userId,
			                                                     @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
		UserRegistrationData userRegistrationData = userRegistrationService.updateUserRegistrationData(userId, userRegistrationDTO);
		ResponseDTO respDTO = new ResponseDTO("Updated Employee Payroll Data Successfully", userRegistrationData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ResponseDTO> deleteUserRegistrationData(@PathVariable("userId") int userId) {
		userRegistrationService.deleteUserRegistrationData(userId);
		ResponseDTO respDTO = new ResponseDTO("Deleted Successfully", "Deleted Id : " + userId);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
}

