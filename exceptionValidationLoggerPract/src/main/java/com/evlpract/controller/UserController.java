package com.evlpract.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.evlpract.RNF.CustomException;
import com.evlpract.Repository.WorkersRepo;
import com.evlpract.service.UserService;
import com.evlpract.user.Users;
import org.springframework.context.MessageSource;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/workers")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/add")
	public ResponseEntity<Users> addWorker(@Valid @RequestBody Users user)
	{
		logger.info("User added");
		
		userService.addWorker(user);
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Users>> getWorkers()
	{

		logger.info("Getting all the workers");
		
		List<Users> users= userService.getWorkers();
		
		logger.info("Got all the users");
		
		return new ResponseEntity<List<Users>>(users,HttpStatus.OK);
		//return users;
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Optional<Users>> getWorker(@PathVariable int id)
	{
		logger.info("Finding the user with this id");
		
		Optional<Users> user=userService.getWorker(id);
		
		logger.info("User Found");
		
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Users> updateWorker(@Valid @RequestBody Users user)
	{
		logger.info("Updating the user");
		
		userService.updateWorker(user);
		
		logger.info("User updated");
		
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/delete")
	public void deleteWorkers()
	{
		logger.info("Deleting all the users");
		
		userService.deleteWorkers();
		
		logger.info("All the users deleted");
	}
	
	@DeleteMapping("/delete/id")
	public void deleteWorker(@PathVariable int id)
	{
		logger.info("Deleting the user with id");
		
		userService.deleteWorker(id);
		
		logger.info("All the users deleted");
	}
	
	
//	public UserController(WorkersRepo workersrepo)
//	{
//		this.workersrepo=workersrepo;
//	}
	
	
}
