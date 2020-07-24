package com.test.automation.ApiAutomation.service;

import java.util.List;

import org.json.JSONObject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.test.automation.ApiAutomation.requestpojo.CreateUserReuest;




public class Service {
	List<JSONObject> list;
	
	/**
	 * This API will create Person
	 * @param name
	 * @param surname
	 * @param city
	 * @param landmark
	 * @param state
	 * @param zipcode
	 * @return
	 */
	
	public Response CeateUser(String name,String job) {
		
		CreateUserReuest createUserReuest =new CreateUserReuest();
		createUserReuest.setName(name);
		createUserReuest.setJob(job);
		JSONObject jsonObj = new JSONObject(createUserReuest);
		System.out.println("json payload..");
		/*
		 * list = new ArrayList<JSONObject>(); list.add(jsonObj);
		 * System.out.println(list);
		 */		
		RestAssured.baseURI="https://reqres.in/api";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(jsonObj.toString());
		Response response = request.post("/users");
		
		return response; 
	}
	
	
}
