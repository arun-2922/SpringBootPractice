package com.evlpract.customvalidate;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CollegeTypeValidator implements ConstraintValidator<CustomValidator,String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		List<String> collegeList=new ArrayList<String>();
		collegeList.add("IIITA");
		collegeList.add("CFA");
		collegeList.add("GL");
		return collegeList.contains(value);
	}
	

}
