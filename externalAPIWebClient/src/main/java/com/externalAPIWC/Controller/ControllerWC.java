package com.externalAPIWC.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.externalAPIWC.Error.ErrorResp;
import com.externalAPIWC.Service.ServiceWC;
import com.externalAPIWC.data.Data;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/exapiwc")
public class ControllerWC {
	
	@Value("${base.url}")
	private String url;
	
	@Autowired
	private ServiceWC serviceWC;
	
	@GetMapping("/")
	public Flux<Data> getUsers()
	{
		return serviceWC.getUsers(url);
	}
	
	@PostMapping("/add")
	public ResponseEntity<ErrorResp> createUser(@RequestBody Data data)
	{
		return serviceWC.createUser(url+"add",data);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ErrorResp> deleteUser(@PathVariable int id)
	{
		return serviceWC.deleteUser(url+"delete/"+id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ErrorResp> updateUser(@RequestBody Data data)
	{
		return serviceWC.updateUser(url+"update",data);
	}
}

