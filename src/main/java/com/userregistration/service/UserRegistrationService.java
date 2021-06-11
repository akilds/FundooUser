package com.userregistration.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.userregistration.util.Response;
import com.userregistration.util.TokenUtil;
import com.userregistration.dto.UserRegistrationDTO;
import com.userregistration.exception.UserRegistrationException;
import com.userregistration.model.UserRegistrationData;
import com.userregistration.repository.UserRegistrationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRegistrationService implements IUserRegistrationService{

	@Autowired
	private UserRegistrationRepository userRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private JavaMailSender mailSender;
	
	//Returns all user data present
	@Override
	public List<UserRegistrationData> getAllUsers(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			log.info("Get All User Data");
			List<UserRegistrationData> getAllUsers = userRepository.findAll();
			return getAllUsers;
		}else {
			log.error("User Token Is Not valid");
			throw new UserRegistrationException(400, "User Token Is Not Valid");
		}	
	}
			
	//Adds a new user data
	@Override
	public Response addUser(UserRegistrationDTO userDTO) {
		Optional<UserRegistrationData> isPresent = userRepository.findByEmailId(userDTO.getMobileNo());
		if(isPresent.isPresent()) {
			log.error("User Already Added");
			throw new UserRegistrationException(400, "User Already Added");
		}else {
			log.info("Add User : " + userDTO);
			UserRegistrationData user = modelmapper.map(userDTO, UserRegistrationData.class);
			userRepository.save(user);
			String token = tokenUtil.createToken(user.getUserId());
			sendMail(userDTO.getEmailId(),token);
			return new Response(200, "User Data Added Successfully", token);
		}	
	}

	@Override
	public Response verifyUser(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			log.debug("User: " + isPresent.get() + " Verified!");
			isPresent.get().setVerify(true);
			userRepository.save(isPresent.get());
			return new Response(200, "User Verified successfully!", token);
		}
		else {
			log.error("User Token Is Not valid");
			throw new UserRegistrationException(400, "User Token Is Not Valid");
		}
	}
	
    public void sendMail(String receiver, String token) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("akilrainadesigan@gmail.com");
		message.setTo(receiver);
		message.setText("Please click on the below link to verify : \n http://localhost:8080/userregistration/verify/"+token);
		message.setSubject("Mail Validation");
		
		mailSender.send(message);
		log.info("Mail Sent");
	}
    
	//Updates an existing user data
	@Override
	public Response updateUser(String token, UserRegistrationDTO userDTO) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			log.info("Update User : " + userDTO);
			isPresent.get().updateUserRegistration(userDTO);
			userRepository.save(isPresent.get());
			return new Response(200, "User Data Updated Successfully", token);
		}else {
			log.error("User Doesnt Exist");
			throw new UserRegistrationException(400, "User Doesnt Exist");
		}	
	}

	//Deletes an existing user data
	@Override
	public Response deleteUser(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			log.info("User Data Deleted");
			userRepository.delete(isPresent.get());
			return new Response(200, "User Data Deleted Successfully", token);
		}else {
			log.error("User Token Is Not Valid");
			throw new UserRegistrationException(400, "User Token Is Not Valid");
		}
	}
}
