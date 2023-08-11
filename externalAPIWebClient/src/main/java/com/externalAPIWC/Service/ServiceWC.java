package com.externalAPIWC.Service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.externalAPIWC.Error.ErrorResp;
import com.externalAPIWC.customexception.CustomException;
import com.externalAPIWC.data.Data;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceWC {
	
	@Autowired
	WebClient webClient;
	
	public Flux<Data> getUsers(String url) {
		
		Flux<Data> response=webClient.get().uri(url).retrieve().bodyToFlux(Data.class);

		
		return response;
	}

	public ResponseEntity<ErrorResp> createUser(String url, Data data) {
		System.out.println(url);
		Data response=null;
		try {
		response=webClient.post().uri(url).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(data))
				.retrieve()
				.bodyToMono(Data.class).block();
		} catch(Exception e)
		{
			throw new CustomException(HttpStatus.BAD_REQUEST,"User already there");
		}
//		response.onErrorResume(Exception.class,ex->{
//			System.out.println("Errorrrr found");
//			return Mono.error(new CustomException(HttpStatus.BAD_REQUEST,"User already there"));
//		});
		
		return new ResponseEntity<ErrorResp>(new ErrorResp(HttpStatus.CREATED.value(),"User added"),HttpStatus.CREATED);
	}

	public ResponseEntity<ErrorResp> deleteUser(String url) {
		Data response=null;
		try {
			response=webClient.delete().uri(url).retrieve().bodyToMono(Data.class).block();
		} catch(Exception e)
		{
			throw new CustomException(HttpStatus.BAD_REQUEST,"User does not exist");
		}
		return new ResponseEntity<ErrorResp>(new ErrorResp(HttpStatus.OK.value(),"User Deleted"),HttpStatus.OK);

	}

	public ResponseEntity<ErrorResp> updateUser(String url, Data data) {
		webClient.put().uri(url).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(data))
		.retrieve()
		.bodyToMono(Void.class).block();
		
		return new ResponseEntity<ErrorResp>(new ErrorResp(HttpStatus.OK.value(),"User updated"),HttpStatus.OK);

	}
	
	
}
