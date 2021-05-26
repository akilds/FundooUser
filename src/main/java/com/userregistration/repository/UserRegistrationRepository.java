package com.userregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userregistration.model.UserRegistrationData;

public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData, Integer>{

}
