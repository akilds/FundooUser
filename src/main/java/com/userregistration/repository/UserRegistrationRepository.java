package com.userregistration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userregistration.model.UserRegistrationData;

public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData, Integer>{

	public Optional<UserRegistrationData> findByEmailId(String emailId);
	
}
