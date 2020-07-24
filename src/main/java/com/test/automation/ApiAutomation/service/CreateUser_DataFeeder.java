package com.test.automation.ApiAutomation.service;

import com.test.automation.ApiAutomation.requestpojo.CreateUserReuest;

public class CreateUser_DataFeeder {
	
	public static CreateUserReuest CreateUserPayload(String name,String job) {
		
		CreateUserReuest createUserRequest=new CreateUserReuest();
		createUserRequest.setName(name);
		createUserRequest.setJob(job);
		
		return createUserRequest;
		
	}

}
