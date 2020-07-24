package com.test.automation.APITest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.test.automation.ApiAutomation.requestpojo.CreateUserReuest;
import com.test.automation.ApiAutomation.responsepojo.CreateUserResponse;
import com.test.automation.ApiAutomation.responsepojo.SingleUserResponse;
import com.test.automation.ApiAutomation.service.CreateUser_DataFeeder;
import com.test.automation.Generic.Base.TestBase;
import com.test.automation.Generic.CommonMethods.APIConstants;
import com.test.automation.Generic.CommonMethods.HTTPMethods;
import com.test.automation.Generic.CommonMethods.ResponseParser;

public class APITest extends TestBase {

	public static final Logger log = Logger.getLogger(APITest.class.getName());

	public static final String CreateUser_URN = "users";

	@DataProvider(name = "CreateUser")
	public String[][] getTestData() {
		String[][] testRecords = getData("TestData.xlsx", "CreateUser");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws IOException {
		initAPI();

	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * 
	 * Service service=new Service(); Response response = service.CeateUser("puja",
	 * "ETL"); String job=response.jsonPath().get("job"); System.out.println(job);
	 * 
	 * Gson gson = new Gson(); CreateUserResponse exampleresponse =
	 * gson.fromJson(response.asString(), CreateUserResponse.class);
	 * 
	 * System.out.println("Response Api is Person name "+exampleresponse.getName());
	 * System.out.println("Response API is job name "+exampleresponse.getJob());
	 * System.out.println("Response API is job Id "+exampleresponse.getId());
	 * System.out.println("Response API is job CreationDate "+exampleresponse.
	 * getCreatedAt());
	 * 
	 * 
	 * //********************************************************
	 * 
	 * CreateUserReuest payload = CreateUser_DataFeeder.CreateUserPayload("Puja",
	 * "ETL"); Response response = HTTPMethods.POST(payload, URN);
	 * CreateUserResponse responseobject = (CreateUserResponse)
	 * ResponseParser.GetObject(response, CreateUserResponse.class);
	 * 
	 * //Assert.assertEquals(response.statusCode(), actual);
	 * System.out.println("Response Api is Person name "+responseobject.getName());
	 * System.out.println("Response API is job name "+responseobject.getJob());
	 * System.out.println("Response API is job Id "+responseobject.getId());
	 * System.out.println("Response API is job CreationDate "+responseobject.
	 * getCreatedAt());
	 * 
	 * 
	 * }
	 */

	@Test(dataProvider = "CreateUser" ,enabled = false)
	public void CreateUserPost(String name,String job) {

		CreateUserReuest payload = CreateUser_DataFeeder.CreateUserPayload(name, job);
		Response response = HTTPMethods.POST(payload, CreateUser_URN);
		CreateUserResponse responseobject = (CreateUserResponse) ResponseParser.GetObject(response,
				CreateUserResponse.class);
		
		
		// Assert.assertEquals(response.statusCode(), actual);
		System.out.println("Response Api is Person name " + responseobject.getName());
		System.out.println("Response API is job name " + responseobject.getJob());
		System.out.println("Response API is job Id " + responseobject.getId());
		System.out.println("Response API is job CreationDate " + responseobject.getCreatedAt());
	}
	
	@Test(enabled = false)
	public void UserGet() {

		
		Response response = HTTPMethods.GET(APIConstants.SingleUser_Get);
		System.out.println(response.statusCode() + " "+response.getBody().asString());
		SingleUserResponse responseobject = (SingleUserResponse) ResponseParser.GetObject(response,
				SingleUserResponse.class);

		// Assert.assertEquals(response.statusCode(), actual);
		System.out.println("Response Api is Data Ad company " + responseobject.getAd().getCompany());
		System.out.println("Response API is Data Ad url " + responseobject.getAd().getUrl());
		System.out.println("Response API is Data Ad text " + responseobject.getAd().getText());
		System.out.println("Response API is Data Avatar  " + responseobject.getData().get(0).getAvatar());
		System.out.println("Response API is data Email  " + responseobject.getData().get(0).getEmail());
		
	}	
	
	
	
	@AfterClass
	public void TearDown() {
	System.out.println("API closed");
	}

}
