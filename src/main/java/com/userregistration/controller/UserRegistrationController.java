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

import com.userregistration.dto.UserRegistrationDTO;
import com.userregistration.model.UserRegistrationData;
import com.userregistration.service.IUserRegistrationService;
import com.userregistration.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/userregistration")
@Slf4j
public class UserRegistrationController {

	@Autowired
	private IUserRegistrationService userRegistrationService;
	
	@GetMapping("getallusersregistered/{token}")
	public ResponseEntity<List<?>> getAllUsersRegistered(@PathVariable String token) {
		log.info("Get All User Data");
		List<UserRegistrationData> response = userRegistrationService.getAllUsers(token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/verify/{token}")
	public ResponseEntity<Response> verifyUser(@PathVariable String token){
		Response userEntity = userRegistrationService.verifyUser(token);
		return new ResponseEntity<Response>(userEntity,HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Response> addUserRegistrationData(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
		log.info("Create User Data : " + userRegistrationDTO);
		Response response  = userRegistrationService.addUser(userRegistrationDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PutMapping("/update/{token}")
	public ResponseEntity<Response> updateUserRegistrationData(@PathVariable String token,
			                                                  @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
		log.info("Update User Data : " + userRegistrationDTO);
		Response response  = userRegistrationService.updateUser(token, userRegistrationDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{token}")
	public ResponseEntity<Response> deleteUserRegistrationData(@PathVariable String token) {
		log.info("User Data Deleted");
		Response response  = userRegistrationService.deleteUser(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}

