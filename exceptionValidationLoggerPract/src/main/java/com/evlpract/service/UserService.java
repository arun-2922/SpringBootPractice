package com.evlpract.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.evlpract.RNF.CustomException;
import com.evlpract.Repository.WorkersRepo;
import com.evlpract.controller.UserController;
import com.evlpract.user.Users;

import jakarta.validation.Valid;

@Service
public class UserService {

	@Autowired
	private WorkersRepo workersrepo;
	
	@Autowired
	private MessageSource messageSource;
	
	Logger logger=LoggerFactory.getLogger(UserController.class);
	
	public Users addWorker(@Valid Users user) {
		workersrepo.save(user);
		return user;
	}

	public List<Users> getWorkers() {

		List<Users> user=workersrepo.findAll();
		if(user.isEmpty())
		{
			logger.error("Your database is empty");
			throw new CustomException(HttpStatus.BAD_REQUEST,messageSource.getMessage("api.error.user.database.empty", null, Locale.ENGLISH));
		}
		
		return user;
	}

	public Optional<Users> getWorker(int id) {
		
		Optional<Users> user=workersrepo.findById(id);
		
		if(user.isEmpty())
		{
			logger.error("The user id is not found");
			throw new CustomException(HttpStatus.NOT_FOUND,messageSource.getMessage("api.error.user.not.found", null, Locale.ENGLISH));
		}
		
		return user;
		
	}

	public void updateWorker(@Valid Users user) {
		workersrepo.save(user);
		
	}

	public void deleteWorkers() {
		workersrepo.deleteAll();
	
	}

	public void deleteWorker(int id) {
		Optional<Users> user=workersrepo.findById(id);
		
		if(user.isEmpty())
		{
			logger.error("The user is not present");
			throw new CustomException(HttpStatus.BAD_REQUEST,messageSource.getMessage("api.error.user.not.found", null, Locale.ENGLISH));
		}
		
		workersrepo.deleteById(id);
		
	}
	
}
