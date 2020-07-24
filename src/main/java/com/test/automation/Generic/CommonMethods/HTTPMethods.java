package com.test.automation.Generic.CommonMethods;

import org.json.JSONObject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class HTTPMethods {
	
	// POST request
	public static Response POST(Object requestclassobject,String urn) {
		
		JSONObject jsonObj = new JSONObject(requestclassobject);
		System.out.println("json payload..");		
		RestAssured.baseURI=APIConstants.BASE_URI;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(jsonObj.toString());
		Response response = request.post(urn);
		
		return response; 
	}
	
	// Get Request
	
public static Response GET(String urn) {		
	
		System.out.println("json payload..");		
		RestAssured.baseURI=APIConstants.BASE_URI;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");	
		Response response = request.get(urn);
		
		return response; 
	}

public static Response PUT(Object requestclassobject,String urn) {
	
	JSONObject jsonObj = new JSONObject(requestclassobject);
	System.out.println("json payload..");		
	RestAssured.baseURI=APIConstants.BASE_URI;
	RequestSpecification request = RestAssured.given();
	request.header("Content-Type", "application/json");
	request.body(jsonObj.toString());
	Response response = request.put(urn);
	
	return response; 
}

public static Response DELETE(String urn) {
	
	System.out.println("json payload..");		
	RestAssured.baseURI=APIConstants.BASE_URI;
	RequestSpecification request = RestAssured.given();
	request.header("Content-Type", "application/json");	
	Response response = request.delete(urn);
	
	return response; 
}
}
