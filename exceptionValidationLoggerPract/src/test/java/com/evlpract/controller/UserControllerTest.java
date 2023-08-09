package com.evlpract.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.evlpract.Repository.WorkersRepo;
import com.evlpract.user.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WorkersRepo workersRepo; 
	
	Users userOne;
	Users userTwo;
	List<Users> usersList = new ArrayList<>();
	
	
	@BeforeEach
	void setUp() {
		userOne = new Users(11,"Arun","IIITA",23);
		userTwo = new Users(12,"Arjun","IIITH",24);
		usersList.add(userOne);
		usersList.add(userTwo);
	}
	
	@Test
	void TestaddWorker() throws Exception
	{
		
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE , false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(userOne);
		
		when(workersRepo.save(userOne)).thenReturn(userOne);
		
		this.mockMvc.perform(post("/workers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.name",is(userOne.getName())));
		
	}
	
	@Test
	void TestgetWorkers() throws Exception
	{
		when(workersRepo.findAll()).thenReturn(usersList);
		
		this.mockMvc.perform(get("/workers/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name",is("Arun")));
	}
	
	@Test
	void TestgetWorker() throws Exception
	{
		when(workersRepo.findById(11)).thenReturn(Optional.of(userOne));
		
		this.mockMvc.perform(get("/workers/user/11")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",notNullValue()))
				.andExpect(jsonPath("$.name",is("Arun")));
		
	}
}

//@ExtendWith(MockitoExtension.class)
//public class UserControllerTest{
//	
//	@InjectMocks
//	UserController userController;
//	
//	@Mock
//	WorkersRepo workersRepo;
//	
//	private MockMvc mockMvc;
//	
//	private final ObjectMapper objectMapper = new ObjectMapper();
//	private final ObjectWriter objectWriter = objectMapper.writer();
//	
//	@BeforeEach
//	public void setup() {
//		//MockitoAnnotations.initMocks(this);
//		mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
//	}
//	
//	@Test
//	void addWorkersTest() throws Exception{
//		
//		//Users user=Users.builder().userId(11).name("Arun").college("IIITA").age(23).build();
//        Users user=new Users(11,"Arun","IIITA",23);
//		//ArgumentCaptor<Users> argumentCaptor= ArgumentCaptor.forClass(Users.class);
//		
//		when(workersRepo.save(user)).thenReturn(user);
//		
//		ResultActions result = this.mockMvc.perform(post("/workers/add")
//				.content(objectMapper.writeValueAsString(user))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON));
//		
//		//verify(workersRepo).save(argumentCaptor.capture());
//        result.andExpect(status().isOk());
//
//	}
//}
