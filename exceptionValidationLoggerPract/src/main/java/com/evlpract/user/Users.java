package com.evlpract.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.evlpract.customvalidate.CustomValidator;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document(collection="workers")
public class Users {
	
//	@Autowired
//	private MessageSource messageSource;
//	
	@Id
	@NotNull(message="{enter.valid.id}")
	private int userId;
	@NotBlank(message="{name.not.empty}")
	private String name;
	@CustomValidator
	private String college;
	@Min(20)
	@Max(25)
	private int age;

}
