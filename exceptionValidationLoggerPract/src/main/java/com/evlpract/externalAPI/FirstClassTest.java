package com.evlpract.externalAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.evlpract.user.Users;


public class FirstClassTest {
	public void getexternalAPI()
	{
		RestTemplate restTemplate= new RestTemplate();
		
		String url="https://jsonplaceholder.typicode.com/todos/1";
	
		ResponseEntity<String> response= restTemplate.getForEntity(url, String.class);
		
		String externalAPIJson=response.getBody();
		
		System.out.println(externalAPIJson);
	}
	
	public void createData() {
		
		RestTemplate restTemplate=new RestTemplate();
		
		String url="http://localhost:8080/workers/add";
		
		HttpEntity<Users> dataRequest=new HttpEntity<Users>(new Users(14,"Harshit","GL",22));
		
		String userCreateResponse = restTemplate.postForObject(url, dataRequest, String.class);
		
		System.out.println(userCreateResponse);
		
		
	}
	
	public static void main(String[] args)
	{
		FirstClassTest firstTest=new FirstClassTest();
		firstTest.getexternalAPI();
		firstTest.createData();
	}
}
